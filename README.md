Что нужно сделать
Выполните поэтапно все шаги ниже на платформе Android для приложения «М.Видео» и при желании реализуйте дополнительное задание. Задавать элементы можно любым способом, который вам больше нравится: через аннотации или методы драйвера.

Скачайте приложение «М.Видео».
В java-проекте задайте в capabilities драйвера параметры: APP_PACKAGE, APP_ACTIVITY и NO_RESET = true.
Установите на эмулятор вручную приложение «М.Видео». Откройте и закройте его пару раз, чтобы закрыть все ознакомительные экраны.
В итоге вы должны увидеть главную страницу приложения.
Теперь переходите к автоматизации тестового сценария:
Проверьте, что вкладка «Каталог» в нижнем таббаре не выбрана.
Перейдите в таббаре на вкладку «Каталог».
Проверьте, что вкладка «Каталог» выбрана.
Реализуйте одинарный скролл вверх и одинарный скролл вниз (можете воспользоваться методом swipe(), который мы создавали на уроке 5 текущего модуля, просто добавив к нему направления UP/DOWN и необходимые координаты, или же написать свою реализацию).
В поле «Название товара» введите «Телевизор» и перейдите к результатам поиска.
Сохраните в переменную foundTitleText текст «Найдено … товаров».
Затем перейдите в «Фильтры».
Активируйте переключатель «Товары со скидкой» и нажмите на красную кнопку внизу «Показать».
Сохраните в переменную foundSaleTitleText текст «Найдено … товаров».
Проверьте, что переменная foundTitleText не равна foundSaleTitleText.
Заблокируйте экран эмулятора на 3 секунды.
Проверьте, что после разблокировки экрана в поисковом поле остался текст «Телевизор».
Добавьте генерацию Allure-отчётности к вашему проекту.
По желанию. Попробуйте сделать свайп влево или вправо не по всему экрану, а по виджету «Уточните категорию».
