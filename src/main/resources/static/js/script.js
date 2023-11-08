document.getElementById('submit-button').addEventListener('click', function() {
    // Получите значение выбранного элемента "shop-catalog"
    var selectedShop = document.getElementById('shop-catalog').value;

    // Создайте скрытый input в форме и установите в него значение "shopName"
    var hiddenInput = document.createElement('input');
    hiddenInput.type = 'hidden';
    hiddenInput.name = 'shopName';
    hiddenInput.value = selectedShop;

    // Добавьте скрытый input к форме
    document.forms['menu-categories-form'].appendChild(hiddenInput);

    // Теперь отправьте форму
    document.forms['menu-categories-form'].submit();
});



///////////////////////////////////////




const categories = [
    {
        "id": 27,
        "active": true,
        "name": "Дисплеи",
        "parentId": 0,
        "rootCategory": false,
        "description": "Дисплеи",
        "metaTitle": "Дисплеи",
        "metaDescription": "Дисплеи",
        "metaKeywords": "Дисплеи",
        "humanReadableUrl": "Дисплеи",
        "urlImage": "Дисплеи",
        "shopName": null
    }, {
        "id": 28,
        "active": true,
        "name": "Модули",
        "parentId": 0,
        "rootCategory": false,
        "description": "Модули",
        "metaTitle": "Модули",
        "metaDescription": "Модули",
        "metaKeywords": "Модули",
        "humanReadableUrl": "Модули",
        "urlImage": "Модули",
        "shopName": null
    }, {
        "id": 29,
        "active": true,
        "name": "Дисплеи Xiaomi",
        "parentId": 27,
        "rootCategory": false,
        "description": "Дисплеи Xiaomi",
        "metaTitle": "Дисплеи Xiaomi",
        "metaDescription": "Дисплеи Xiaomi",
        "metaKeywords": "Дисплеи Xiaomi",
        "humanReadableUrl": "Дисплеи Xiaomi",
        "urlImage": "Дисплеи Xiaomi",
        "shopName": null
    }, {
        "id": 30,
        "active": true,
        "name": "Модули Xiaomi",
        "parentId": 28,
        "rootCategory": false,
        "description": "Модули Xiaomi",
        "metaTitle": "Модули Xiaomi",
        "metaDescription": "Модули Xiaomi",
        "metaKeywords": "Модули Xiaomi",
        "humanReadableUrl": "Модули Xiaomi",
        "urlImage": "Модули Xiaomi",
        "shopName": null
    }, {
        "id": 31,
        "active": true,
        "name": "Дисплеи Samsung",
        "parentId": 27,
        "rootCategory": false,
        "description": "Дисплеи Samsung",
        "metaTitle": "Дисплеи Samsung",
        "metaDescription": "Дисплеи Samsung",
        "metaKeywords": "Дисплеи Samsung",
        "humanReadableUrl": "Дисплеи Samsung",
        "urlImage": "Дисплеи Samsung",
        "shopName": null
    }, {
        "id": 32,
        "active": true,
        "name": "Модули Samsung",
        "parentId": 28,
        "rootCategory": false,
        "description": "Модули Samsung",
        "metaTitle": "Модули Samsung",
        "metaDescription": "Модули Samsung",
        "metaKeywords": "Модули Samsung",
        "humanReadableUrl": "Модули Samsung",
        "urlImage": "Модули Samsung",
        "shopName": null
    }, {
        "id": 33,
        "active": true,
        "name": "Дисплей Xiaomi Redmi 10",
        "parentId": 29,
        "rootCategory": false,
        "description": "Дисплей Xiaomi Redmi 10",
        "metaTitle": "Дисплей Xiaomi Redmi 10",
        "metaDescription": "Дисплей Xiaomi Redmi 10",
        "metaKeywords": "Дисплей Xiaomi Redmi 10",
        "humanReadableUrl": "Дисплей Xiaomi Redmi 10",
        "urlImage": "Дисплей Xiaomi Redmi 10",
        "shopName": null
    }, {
        "id": 34,
        "active": true,
        "name": "Дисплей Xiaomi Redmi 12",
        "parentId": 29,
        "rootCategory": false,
        "description": "Дисплей Xiaomi Redmi 12",
        "metaTitle": "Дисплей Xiaomi Redmi 12",
        "metaDescription": "Дисплей Xiaomi Redmi 12",
        "metaKeywords": "Дисплей Xiaomi Redmi 12",
        "humanReadableUrl": "Дисплей Xiaomi Redmi 12",
        "urlImage": "Дисплей Xiaomi Redmi 12",
        "shopName": null
    }, {
        "id": 35,
        "active": true,
        "name": "Модуль Xiaomi Redmi 7",
        "parentId": 30,
        "rootCategory": false,
        "description": "Модуль Xiaomi Redmi 7",
        "metaTitle": "Модуль Xiaomi Redmi 7",
        "metaDescription": "Модуль Xiaomi Redmi 7",
        "metaKeywords": "Модуль Xiaomi Redmi 7",
        "humanReadableUrl": "Модуль Xiaomi Redmi 7",
        "urlImage": "Модуль Xiaomi Redmi 7",
        "shopName": null
    }, {
        "id": 36,
        "active": true,
        "name": "Модуль Xiaomi Redmi 8",
        "parentId": 30,
        "rootCategory": false,
        "description": "Модуль Xiaomi Redmi 8",
        "metaTitle": "Модуль Xiaomi Redmi 8",
        "metaDescription": "Модуль Xiaomi Redmi 8",
        "metaKeywords": "Модуль Xiaomi Redmi 8",
        "humanReadableUrl": "Модуль Xiaomi Redmi 8",
        "urlImage": "Модуль Xiaomi Redmi 8",
        "shopName": null
    }, {
        "id": 37,
        "active": true,
        "name": "Дисплей Samsung Galaxy S20",
        "parentId": 31,
        "rootCategory": false,
        "description": "Дисплей Samsung Galaxy S20",
        "metaTitle": "Дисплей Samsung Galaxy S20",
        "metaDescription": "Дисплей Samsung Galaxy S20",
        "metaKeywords": "Дисплей Samsung Galaxy S20",
        "humanReadableUrl": "Дисплей Samsung Galaxy S20",
        "urlImage": "Дисплей Samsung Galaxy S20",
        "shopName": null
    }, {
        "id": 38,
        "active": true,
        "name": "Дисплей Samsung Galaxy S21",
        "parentId": 31,
        "rootCategory": false,
        "description": "Дисплей Samsung Galaxy S21",
        "metaTitle": "Дисплей Samsung Galaxy S21",
        "metaDescription": "Дисплей Samsung Galaxy S21",
        "metaKeywords": "Дисплей Samsung Galaxy S21",
        "humanReadableUrl": "Дисплей Samsung Galaxy S21",
        "urlImage": "Дисплей Samsung Galaxy S21",
        "shopName": null
    }, {
        "id": 39,
        "active": true,
        "name": "Модуль Samsung Galaxy S25",
        "parentId": 32,
        "rootCategory": false,
        "description": "Модуль Samsung Galaxy S25",
        "metaTitle": "Модуль Samsung Galaxy S25",
        "metaDescription": "Модуль Samsung Galaxy S25",
        "metaKeywords": "Модуль Samsung Galaxy S25",
        "humanReadableUrl": "Модуль Samsung Galaxy S25",
        "urlImage": "Модуль Samsung Galaxy S25",
        "shopName": null
    }, {
        "id": 40,
        "active": true,
        "name": "Модуль Samsung Galaxy S30",
        "parentId": 32,
        "rootCategory": false,
        "description": "Модуль Samsung Galaxy S30",
        "metaTitle": "Модуль Samsung Galaxy S30",
        "metaDescription": "Модуль Samsung Galaxy S30",
        "metaKeywords": "Модуль Samsung Galaxy S30",
        "humanReadableUrl": "Модуль Samsung Galaxy S30",
        "urlImage": "Модуль Samsung Galaxy S30",
        "shopName": null
    }
];

const categoryTree = document.getElementById("category-tree");

function createCategoryList(parentId) {
    const ul = document.createElement("ul");
    categories.forEach(category => {
        if (category.parentId === parentId) {
            const li = document.createElement("li");
            li.innerHTML = `<span class="toggle-button"></span> ${category.name}`;
            const childCategories = createCategoryList(category.id);
            if (childCategories.childNodes.length > 0) {
                li.querySelector(".toggle-button").addEventListener("click", () => {
                    childCategories.style.display = childCategories.style.display === "none" ? "block" : "none";
                    li.querySelector(".toggle-button").classList.toggle("open");
                });
                li.appendChild(childCategories);
            }
            ul.appendChild(li);
        }
    });
    return ul;
}

categoryTree.appendChild(createCategoryList(0));