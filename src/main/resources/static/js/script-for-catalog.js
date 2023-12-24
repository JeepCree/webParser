let catId;
let descIds;
let selectedStore;
categoriesJson = categoriesJson;

//СОЗДАНИЕ КАТАЛОГА КАТЕГОРИЙ
// document.addEventListener("DOMContentLoaded", function() {
//     const categories = JSON.parse(categoriesJson);
//     const categoryTree = document.getElementById("category-tree");
//
//     function createCategoryList(parentId) {
//         const ul = document.createElement("ul");
//         ul.style.display = parentId === 0 ? "block" : "none";
//
//         categories.forEach((category) => {
//             if (category.parentId === parentId) {
//                 const li = document.createElement("li");
//                 const link = document.createElement("a");
//                 link.setAttribute("data-id", category.id);
//                 link.textContent = category.name;
//                 link.classList.add("category-link");
//                 li.appendChild(link);
//
//                 const childCategories = createCategoryList(category.id);
//
//                 if (childCategories.childNodes.length > 0) {
//                     const toggleButton = document.createElement("span");
//                     toggleButton.className = "toggle-button";
//                     toggleButton.addEventListener("click", () => {
//                         childCategories.style.display = childCategories.style.display === "none" ? "block" : "none";
//                         toggleButton.classList.toggle("open");
//                     });
//                     li.insertBefore(toggleButton, link);
//                     li.appendChild(childCategories);
//                 } else {
//                     link.style.marginLeft = "10px";
//                 }
//
//                 ul.appendChild(li);
//
//                 link.addEventListener("click", (event) => {
//                     event.preventDefault();  // Отмена действия по умолчанию
//                     const categoryId = link.getAttribute("data-id");
//                     const descendantIds = getDescendantIds(category);
//                     const idsToSend = [categoryId, ...descendantIds].join(',');
//                     catId = categoryId;
//                     descIds = descendantIds;
//                     fetch('/get-all-catalog', {
//                         method: 'POST',
//                         headers: {
//                             'Content-Type': 'application/json'
//                         },
//                         body: JSON.stringify({ categoryIds: idsToSend })
//                     })
//                         .then(response => {
//                             if (!response.ok) {
//                                 throw new Error(`Network response was not ok: ${response.status}`);
//                             }
//                             return response.text();
//                         })
//                         .then(data => {
//                             const parser = new DOMParser();
//                             const updatedTableBody = parser.parseFromString(data, 'text/html').body.querySelector('tbody');
//
//                             // Находим текущее тело таблицы
//                             const currentTableBody = document.querySelector('#productTable tbody');
//
//                             // Удаляем все дочерние элементы из текущего тела таблицы
//                             while (currentTableBody.firstChild) {
//                                 currentTableBody.removeChild(currentTableBody.firstChild);
//                             }
//                             // Добавляем новые строки
//                             updatedTableBody.childNodes.forEach(node => {
//                                 currentTableBody.appendChild(node.cloneNode(true));
//                             });
//                         })
//                         .catch(error => {
//                             console.error('Ошибка запроса:', error);
//                         });
//                 });
//             }
//         });
//
//         return ul;
//     }
//
//     categoryTree.appendChild(createCategoryList(0));
//     function getDescendantIds(category) {
//         let descendantIds = [];
//         categories.forEach((c) => {
//             if (c.parentId === category.id) {
//                 descendantIds.push(c.id);
//                 descendantIds = descendantIds.concat(getDescendantIds(c));
//             }
//         });
//         return descendantIds;
//     }
// });

//ФУНКЦИЯ ПОДТВЕРЖДЕНИЯ УДАЛЕНИЯ
let deleteButtons = document.querySelectorAll('.delete-button');
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

//РЕДАКТИРОВАНИЕ СТРОКИ В ТАБЛИЦЕ
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

//СОХРАНЕНИЕ СТРОКИ В ТАБЛИЦЕ
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
            const columnName = cell.classList.contains('table-sheet-name') ? 'name' :
                cell.classList.contains('table-sheet-stock') ? 'stock' :
                    cell.classList.contains('table-sheet-price') ? 'price' :
                        cell.classList.contains('table-sheet-link') ? 'link' : '';

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
                    // Проверяем, является ли data равным true
                    if (data === true) {
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
}

//СОРТИРОВКА СТОЛБЦОВ ТАБЛИЦЫ
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

//ОБНОВЛЕНИЕ ТАБЛИЦЫ В ЗАВИСИМОСТИ ОТ ЗНАЧЕНИЯ ПОИСКА
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
            const articleColumnValue = row.querySelector('.td-table-sheet-article').innerText.toLowerCase();
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

//ОЧИСТКА ЗНАЧЕНИЯ ПОИСКА
function clearSearch() {
    const searchInput = document.querySelector('.search-input');
    searchInput.value = '';
    // Дополнительные действия, которые могут быть необходимы после очистки
}

//ОТКРЫТИЕ КАРТОЧКИ ДОБАВЛЕНИЯ ТОВАРА
function openAddNewProduct() {
    let div = document.getElementById('add-new-product');
    div.style.display = 'block';
    document.getElementById('catIdInput').value = catId;
}

//УДАЛЕНИЕ ПРОДУКТА
function deleteProduct(itemId) {
    // Создаем объект FormData и добавляем параметр itemId
    var formData = new FormData();
    formData.append('catId', itemId);

    // Опции для запроса
    var options = {
        method: 'POST',
        body: formData,
        // Указываем, что ожидаем ответ в формате JSON
        headers: {
            'Accept': 'application/json',
            // другие заголовки...
        },
    };

    // Выполняем запрос
    fetch('/delete-product', options)
        .then(response => response.json())
        .then(data => {
            // Обработка успешного ответа от сервера
            if (data == true){

                // вставка

                const idsToSend = [catId, ...descIds].join(',');
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
                // конец вставки





            } else {
                alert("Товар " + itemId + " НЕ удален!");
            }
        })
        .catch(error => {
            // Обработка ошибок
            console.error('Error:', error);
        });
}

//СОХРАНЕНИЕ НОВОГО ТОВАРА
document.querySelector('form#saveProduct').addEventListener('submit', function (event) {
    event.preventDefault(); // Предотвращение стандартной отправки формы
    function sendData() {
        // Получение значений полей формы
        var catId = document.getElementById('catIdInput').value;
        var name = document.getElementById('name').value;
        var stock = document.getElementById('stock').value;
        var price = document.getElementById('price').value;
        var link = document.getElementById('link').value;

        // Создание объекта FormData для упрощения передачи данных
        var formData = new FormData();
        formData.append('catId', catId);
        formData.append('name', name);
        formData.append('stock', stock);
        formData.append('price', price);
        formData.append('link', link);

        // Отправка данных на сервер с использованием fetch
        fetch('/add-new-product', {
            method: 'POST',
            body: formData
        })
            .then(response => {
                // Проверка статуса ответа
                if (!response.ok) {
                    throw new Error('Ошибка при отправке данных на сервер.');
                }
                return response.json();
            })
            .then(data => {
                // Обработка успешного ответа от сервера
                if (data == true){

                    // вставка

                    const idsToSend = [catId, ...descIds].join(',');
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
                    // конец вставки





                } else {
                    alert("Товар " + itemId + " НЕ добавлен!");
                }
            })



            .catch(error => {
                // Обработка ошибки
                console.error(error);
            });
    }
    sendData(); // Вызов функции отправки данных
    closeAddNewProduct();
});

//ЗАКРЫТИЕ КАРТОЧКИ ДОБАВЛЕНИЯ ТОВАРА
function closeAddNewProduct() {
    // Реализуйте логику закрытия окна, если необходимо
        let div = document.getElementById('add-new-product');
        div.style.display = 'none';
}
function closeEditProduct() {
    // Реализуйте логику закрытия окна, если необходимо
    let div = document.getElementById('full-edit-product');
    div.style.display = 'none';
}

//ОТПРАВКА ДАННЫХ О ВЫБРАННОМ МАГАЗИНЕ (дописать логику)
function sendSelectedStore() {
    const selectElement = document.getElementById('storeSelect');
    const selectedStore = selectElement.value;
    // Отправка выбранного магазина на сервер (замените '/api/save-store' на ваш реальный эндпоинт)
    fetch('/get-store-catalog', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ store: selectedStore })
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Network response was not ok: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            console.log(data);
            if (data !== "false"){
                //начало

                const categories = data;
                const categoryTree = document.getElementById("category-tree");
                const productTableBody = document.querySelector('#productTable tbody');
                categoryTree.innerHTML = '';
                productTableBody.innerHTML = '';
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
                                catId = categoryId;
                                descIds = descendantIds;
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
                                    // .then(data => {
                                    //     const parser = new DOMParser();
                                    //     const updatedTableBody = parser.parseFromString(data, 'text/html').body.querySelector('tbody');
                                    //     // Добавляем новые строки
                                    //     updatedTableBody.childNodes.forEach(node => {
                                    //         productTableBody.appendChild(node.cloneNode(true));
                                    //     });
                                    // })
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
                //конец
            } else {
                console.log('Ответ от сервера:', data);
            }

        })
        .catch(error => {
            console.error('Ошибка запроса:', error);
        });
}

function closeEditCategory() {
    // Replace 'newPage.html' with the URL of the page you want to redirect to
    window.location.href = '/catalog-settings';
}




///////////////////////////////////////////////

function showContextMenu(event) {
    event.preventDefault();

    var contextMenu = document.getElementById("context-menu");

    // Получаем координаты события относительно таблицы
    var tableCoordinates = document.getElementById("productTable").getBoundingClientRect();

    // Устанавливаем положение контекстного меню относительно таблицы
    contextMenu.style.display = "block";
    contextMenu.style.left = event.clientX - tableCoordinates.left + 10 + "px";
    contextMenu.style.top = event.clientY - tableCoordinates.top + 10 + "px";

    window.selectedRow = event.currentTarget;
}

function openEditNewProduct() {
    let div = document.getElementById('full-edit-product');
    div.style.display = 'block';
}

function editProduct() {

    // Получаем данные о товаре с сервера (замените на свой код загрузки данных)
    var productId = window.selectedRow.getAttribute('data-id');
    var article = window.selectedRow.querySelector('.td-table-sheet-article').innerText;
    var name = window.selectedRow.querySelector('.table-sheet-name .editable-content').innerText;
    var stock = window.selectedRow.querySelector('.table-sheet-stock .editable-content').innerText;
    var price = window.selectedRow.querySelector('.table-sheet-price .editable-content').innerText;

    fetch('/full-edit-product', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ article: article })
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
            console.log("DATA - " + data);
        })
        .catch(error => {
            console.error('Ошибка запроса:', error);
        });

    openEditNewProduct();
    // Замените следующую строку на ваш код для загрузки данных с сервера
    // Вместо алерта можно открыть модальное окно для редактирования
    // alert("Редактирование товара\nID: " + productId + "\nArticle: " + article + "\nName: " + name + "\nStock: " + stock + "\nPrice: " + price);
}

function delProduct(){
    var productId = window.selectedRow.getAttribute('data-id');
    deleteProduct(productId);
}

document.addEventListener("click", function () {
    var contextMenu = document.getElementById("context-menu");
    contextMenu.style.display = "none";
});
