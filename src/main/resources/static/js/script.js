
document.addEventListener("DOMContentLoaded", function() {
    const categories = JSON.parse(categoriesJson);

    // console.log('Categories:', categories);

    const categoryTree = document.getElementById("category-tree");
    function createCategoryList(parentId) {
        const ul = document.createElement("ul");
        ul.style.display = parentId === 0 ? "block" : "none"; // Изменение стиля отображения в зависимости от parentId
        categories.forEach((category, index, array) => {
            if (category.parentId === parentId) {
                const li = document.createElement("li");
                const link = document.createElement("a");
                link.href = `#category-${category.id}`; // Установка href для отправки запроса на сервер (замените на ваш URL)
                link.textContent = category.name;
                link.classList.add("category-link"); // Добавление класса для стилей ссылки
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
                    link.style.marginLeft = "20px"; // Добавление отступа для последней вложенной категории
                }
                ul.appendChild(li);

                // Добавление обработчика события для клика по ссылке
                link.addEventListener("click", (event) => {
                    event.preventDefault();
                    // Вместо alert можно добавить ваш код для отправки запроса на сервер с ID категории
                    console.log(`Отправить запрос на сервер с ID категории: ${category.id}`);
                });
            }
        });
        return ul;
    }

    categoryTree.appendChild(createCategoryList(0));
});
