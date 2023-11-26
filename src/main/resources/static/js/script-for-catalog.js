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
                    link.style.marginLeft = "20px";
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
                        .then(data => {
                            document.body.innerHTML = data;
                            const updatedCategoryTree = document.getElementById("category-tree");
                            updatedCategoryTree.innerHTML = "";
                            updatedCategoryTree.appendChild(createCategoryList(0));
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
// Перебираем каждую кнопку и добавляем обработчик события
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