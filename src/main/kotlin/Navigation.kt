import java.util.Scanner

class Navigator(private val scanner: Scanner) { // объявляем класс для навигации по приложению
     private val archives = mutableListOf<Archive>() // создадим новый список, состоящий из пустых архивов

    fun start(){ //отсюда начнём работу приложения
        navigateTo(MainScreen(this, scanner, archives)) //сразу обратимся к главному экрану
    }

    fun navigateTo(screen: Screen) { //создадим метод для переключения между экранами
        screen.display() // общаемся к экземпляру класса, реализующего интерфейс, а потом уже к оверрейженному методу
    }
}