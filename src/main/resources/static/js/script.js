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