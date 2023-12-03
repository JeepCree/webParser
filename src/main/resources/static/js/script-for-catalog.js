document.addEventListener("DOMContentLoaded", function() {
    const categories = JSON.parse(categoriesJson);
    const categoryTree = document.getElementById("category-tree");

    function createCategoryList(parentId) {
        const ul = document.createElement("ul");
        ul.style.display = parentId === 0 ? "block" : "none";

        categories.forEach((category) => {
            if (category.parentId === parentId) {
                const li = document.createElement("li");
                const link = document.createElement("a");
                link.setAttribute("data-id", category.id);
                link.textContent = category.name;
                link.classList.add("category-link");
                li.appendChild(link);

                const childCategories = createCategoryList(category.id);

                if (childCategories.childNodes.length > 0) {
                    const toggleButton = document.createElement("span");
                    toggleButton.className = "toggle-button";
                    toggleButton.addEventListener("click", () => {
                        childCategories.style.display = childCategories.style.display === "none" ? "block" : "none";
                        toggleButton.classList.toggle("open");
                    });
                    li.insertBefore(toggleButton, link);
                    li.appendChild(childCategories);
                } else {
                    link.style.marginLeft = "10px";
                }

                ul.appendChild(li);

                link.addEventListener("click", (event) => {
                    event.preventDefault();  // Отмена действия по умолчанию
                    const categoryId = link.getAttribute("data-id");
                    const descendantIds = getDescendantIds(category);
                    const idsToSend = [categoryId, ...descendantIds].join(',');

                    fetch('/get-all-catalog', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({ categoryIds: idsToSend })
                    })
                        .then(response => {
                            if (!response.ok) {
                                throw new Error(`Network response was not ok: ${response.status}`);
                            }
                            return response.text();
                        })
                        // .then(data => {
                        //     document.body.innerHTML = data;
                        //     const updatedCategoryTree = document.getElementById("category-tree");
                        //     updatedCategoryTree.innerHTML = "";
                        //     updatedCategoryTree.appendChild(createCategoryList(0));
                        // })
                        .then(data => {
                            const parser = new DOMParser();
                            const updatedTableBody = parser.parseFromString(data, 'text/html').body.querySelector('tbody');

                            // Находим текущее тело таблицы
                            const currentTableBody = document.querySelector('#productTable tbody');

                            // Удаляем все дочерние элементы из текущего тела таблицы
                            while (currentTableBody.firstChild) {
                                currentTableBody.removeChild(currentTableBody.firstChild);
                            }

                            // Добавляем новые строки
                            updatedTableBody.childNodes.forEach(node => {
                                currentTableBody.appendChild(node.cloneNode(true));
                            });
                        })
                        .catch(error => {
                            console.error('Ошибка запроса:', error);
                        });
                });
            }
        });

        return ul;
    }

    categoryTree.appendChild(createCategoryList(0));

    function getDescendantIds(category) {
        let descendantIds = [];
        categories.forEach((c) => {
            if (c.parentId === category.id) {
                descendantIds.push(c.id);
                descendantIds = descendantIds.concat(getDescendantIds(c));
            }
        });
        return descendantIds;
    }
});

var deleteButtons = document.querySelectorAll('.delete-button');
deleteButtons.forEach(function(button) {
    button.addEventListener('click', function(event) {
        // Остановим отправку формы
        event.preventDefault();

        // Здесь можно вывести модальное окно с вопросом о подтверждении
        if (confirm('Вы уверены, что хотите удалить этого пользователя? Все данные о транзакциях пользователя так же будут удалены!')) {
            // Если пользователь подтверждает, отправляем форму
            event.target.closest('form').submit();
        }
    });
});

function enableEditing(cell) {
    // Добавляем класс для стилизации при редактировании
    cell.classList.add('highlighted-cell');

    // Находим внутренний элемент, который содержит контент
    const editableContent = cell.querySelector('.editable-content');

    // Проверяем, что элемент был найден, прежде чем устанавливать атрибут contentEditable
    if (editableContent) {
        // Устанавливаем атрибут contentEditable в true для редактирования
        editableContent.contentEditable = 'true';

        // Сохраняем оригинальное значение ячейки
        cell.setAttribute('data-original-value', editableContent.innerText.trim());

        // Устанавливаем фокус на редактируемый элемент
        editableContent.focus();
    }
}
function saveData(cell) {
    // Находим внутренний элемент, который содержит контент
    const editableContent = cell.querySelector('.editable-content');

    // Проверяем, что элемент был найден, прежде чем устанавливать атрибут contentEditable
    if (editableContent) {
        // Устанавливаем атрибут contentEditable в false после редактирования
        editableContent.contentEditable = 'false';

        // Получаем новое значение из ячейки
        const newValue = editableContent.innerText.trim();

        // Проверка, изменилось ли значение
        const originalValue = cell.getAttribute('data-original-value');
        if (newValue !== originalValue) {
            // Собираем данные для отправки на сервер
            const rowId = cell.parentElement.dataset.id;
            console.log('row ID: ' + rowId);
            const columnName = cell.classList.contains('table-sheet-name') ? 'name' :
                cell.classList.contains('table-sheet-stock') ? 'stock' :
                    cell.classList.contains('table-sheet-price') ? 'price' :
                        cell.classList.contains('table-sheet-link') ? 'link' : '';
            console.log('column Name: ' + columnName);
            console.log('new Value: ' + newValue);

            // Отправляем данные на сервер (здесь нужно реализовать отправку данных на ваш сервер)
            fetch('save-sheet', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    rowId: rowId,
                    columnName: columnName,
                    newValue: newValue
                }),
            })
                .then(response => response.json())
                .then(data => {
                    console.log('Success:', data);

                    // Проверяем, является ли data равным true
                    if (data === true) {
                        // Если успешно, показываем оповещение
                        // Swal.fire({
                        //     title: 'Успешно!',
                        //     text: 'Операция выполнена успешно.',
                        //     icon: 'success',
                        //     confirmButtonText: 'OK'
                        // });
                        setTimeout(() => {
                            cell.classList.add('highlighted-cell-ok');
                            setTimeout(() => {
                                // Удаляем класс через 5 секунд
                                cell.classList.remove('highlighted-cell-ok');
                            }, 2000);
                        }, 0);
                    } else {
                        // В противном случае вы можете добавить обработку ошибок или другую логику
                        editableContent.innerText = originalValue;
                        // Swal.fire({
                        //     title: 'Ошибка!',
                        //     text: 'Операция не выполнена успешно.',
                        //     icon: 'error',
                        //     confirmButtonText: 'OK'
                        // });
                        setTimeout(() => {
                            cell.classList.add('highlighted-cell-err');
                            setTimeout(() => {
                                // Удаляем класс через 5 секунд
                                cell.classList.remove('highlighted-cell-err');
                            }, 5000);
                        }, 0);
                    }
                })
                .catch((error) => {
                    console.error('Error:', error);
                });
        }

        // Удаляем классы после редактирования
        cell.classList.remove('highlighted-cell');
    }

    console.log('Focusout event processed.');
}

document.addEventListener('DOMContentLoaded', function () {
    const table = document.getElementById('productTable');
    const tbody = table.querySelector('tbody');

    let currentSortColumn = null;
    let isAscending = true;

    function sortTable(columnIndex) {
        const rows = Array.from(tbody.querySelectorAll('tr'));

        rows.sort((a, b) => {
            const aValue = a.cells[columnIndex].textContent.trim();
            const bValue = b.cells[columnIndex].textContent.trim();

            return isNaN(aValue) || isNaN(bValue)
                ? aValue.localeCompare(bValue)
                : parseFloat(aValue) - parseFloat(bValue);
        });

        if (!isAscending) {
            rows.reverse();
        }

        tbody.innerHTML = '';
        rows.forEach(row => tbody.appendChild(row));
    }

    function handleHeaderClick(event) {
        const clickedColumnIndex = Array.from(event.target.parentNode.cells).indexOf(event.target);

        if (clickedColumnIndex !== -1) {
            if (currentSortColumn === clickedColumnIndex) {
                isAscending = !isAscending;
            } else {
                currentSortColumn = clickedColumnIndex;
                isAscending = true;
            }
            sortTable(clickedColumnIndex);
        }
    }

    table.querySelector('thead').addEventListener('click', handleHeaderClick);
});

document.addEventListener("DOMContentLoaded", function () {
    const searchInput = document.querySelector('.search-input');
    const table = document.getElementById('productTable');
    const tbody = table.querySelector('tbody');

    // Функция для обновления таблицы в зависимости от значения поиска
    function updateTable(searchValue) {
        // Получаем все строки таблицы
        const rows = Array.from(tbody.querySelectorAll('tr'));

        // Фильтруем строки по значениям в колонках "Article" и "Name"
        rows.forEach(row => {
            const articleColumnValue = row.querySelector('.table-sheet-article').innerText.toLowerCase();
            const nameColumnValue = row.querySelector('.table-sheet-name .editable-content').innerText.toLowerCase();

            if (articleColumnValue.includes(searchValue) || nameColumnValue.includes(searchValue)) {
                row.style.display = '';
            } else {
                row.style.display = 'none';
            }
        });
    }

    // Слушатель события ввода для поля поиска
    searchInput.addEventListener('input', function () {
        const searchValue = searchInput.value.toLowerCase();
        updateTable(searchValue);
    });

    // Слушатель события клика для кнопки очистки
    const clearButton = document.querySelector('.clear-button');
    clearButton.addEventListener('click', function () {
        // Очищаем значение поля ввода
        searchInput.value = '';
        // Обновляем таблицу с пустым значением поиска
        updateTable('');
    });
});

function clearSearch() {
    const searchInput = document.querySelector('.search-input');
    searchInput.value = '';
    // Дополнительные действия, которые могут быть необходимы после очистки
}

function addNewProduct() {
    var div = document.getElementById('add-new-product');
    div.innerText('add');
    div.style.display = (div.style.display === 'block') ? 'none' : 'block';


}


