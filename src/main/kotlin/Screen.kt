import java.util.InputMismatchException
import java.util.Scanner

interface Screen { // создаём интерфейс
    fun display()
}

class MainScreen(
        private val navigator: Navigator,
        private val scanner: Scanner,
        private val archives: MutableList<Archive>
) : Screen { // реализуем интерфейс

    override fun display(){ // оверрейдим метод интерфейса
        while (true) { // запускаем цикл
            println("Список архивов") // название с последующим ниже выводом меню
            println("0 - Создать архив")
            for (i in archives.indices) {
                println("${i + 1} - ${archives[i].name}")
            }
            println("${archives.size + 1} - Выход")

            try{
                when (val choice = scanner.nextInt()) { //условия для создания или просмотра архивов, или выхода из списка архивов
                    0 -> createArchive() // в этом случае создаём
                    in 1..archives.size -> navigator.navigateTo(ArchiveScreen(navigator, scanner, archives[choice-1])) // в этом - переходим в архив
                    archives.size + 1 -> return // в этом - возвращаемся
                    else -> println("Некорректный ввод")
                }
            } catch (e: InputMismatchException) {
                println("Пожалуйста, введите значение соответствующее значениям меню")
                scanner.nextLine()
            }
        }
    }

    fun createArchive(){ // создаём архив
        println("Введи название архива") // вывод сообщения о названии архива
        val name = scanner.next() // вводим имя архива
        archives.add(Archive(name)) // добавляем в список архивов архив с указанным именем
    }
}

class ArchiveScreen(
        private val navigator: Navigator,
        private val scanner: Scanner,
        private val archive: Archive
) : Screen { // реализуем интерфейс
    override fun display() { // оверрейдим метод интерфейса
        while (true) { // запускаем цикл
            println("Список заметок архива ${archive.name}") // название с последующим ниже выводом меню
            println("0 - Создать заметку")
            for (i in archive.mapNotes.indices) {
                println("${i + 1} - ${archive.mapNotes[i].textNote}")
            }
            println("${archive.mapNotes.size + 1} - Назад")


            try {
                when (val choice = scanner.nextInt()) { //условия для создания или просмотра заметок, или выхода из списка заметок
                    0 -> createNote() // в этом случае создаём
                    in 1..archive.mapNotes.size -> navigator.navigateTo(NoteScreen(navigator, scanner, archive.mapNotes[choice - 1], this)) // в этом - переходим к заметкам
                    archive.mapNotes.size + 1 -> return // в этом - возвращаемся
                    else -> println("Некорректный ввод")
                }
            } catch (e: InputMismatchException) {
                println("Пожалуйста, введите значение соответствующее значениям меню")
                scanner.nextLine()
            }
        }
    }

    fun createNote(){ // создаём заметку
        println("Введи название заметки") // вывод сообщения о названии заметки
        val name = scanner.next() // вводим имя заметки
        println("Введите текст заметки") // вывод сообщения о тексте заметки
        val content = scanner.next() // вводим нужную нам заметку
        archive.mapNotes.add(Note(name, content)) // добавляем в список заметок определённого архива заметку с указанным именем
    }
}

class NoteScreen(
        private val navigator: Navigator,
        private val scanner: Scanner,
        private val note: Note,
        private val previousScreen: Screen
) : Screen {
    override fun display() {
        println("Заметка: ${note.textNote}") // выводим текст заметки
        println("Содержимое ${note.content}")
        println("Нажмите любую клавишу, чтобы вернуться назад")
        scanner.next()
    }
}