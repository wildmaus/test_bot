package com.justai.jaicf.template

data class Question(val question: String, val options: List<String>, val answer: String)

val questions: List<Question> = listOf(
    Question(
        question = "Что из этого в настоящее время не поддерживается в Kotlin ?",
        options = listOf(
            "JVM", "JavaScript", "LLVM", ".NET CLR"
        ),
        answer = "4"
    ),
    Question(
        question = """
            Какое выражение Kotlin эквивалентно данному из Java?
            *int x = a ? b : c*
        """.trimIndent(),
        options = listOf(
            "*val x = a ?: b, c*",
            "*val x = if(a) b : c*",
            "*val x = a ? b : c*",
            "*val x = if(a) b else c*"
        ),
        answer = "4"
    ),
    Question(
        question = "Есть ли у Kotlin примитивные типы данных, такие как *int*, *long*, *float*?",
        options = listOf(
            "Нет, Kotlin не имеет и не использует примитивные типы данных.",
            "Нет, не на уровне языка. Но компилятор Kotlin использует примитивы JVM для лучшей производительности.",
            "Да, но Kotlin всегда конвертирует их в не примитивные аналоги.",
            "Да, Kotlin в этом отношении похож на Java."
        ),
        answer = "2"
    ),
    Question(
        question = """
            Что такое *to* в приведенном ниже примере:
            *val test = 33 to 42*
        """.trimIndent(),
        options = listOf(
            "Инфиксная функция, создающая пару (33, 42)",
            "Ключевое слово Kotlin для создания пары (33, 42)",
            "Ключевое слово для создания диапазона от 33 до 42",
            "Опечатка"
        ),
        answer = "1"
    ),
    Question(
        question = "Какое из объявлений функций является валидным?",
        options = listOf(
            "int sum(int a, int b)",
            "int sum(a: Int, b: Int)",
            "function sum(a: Int, b: Int): Int",
            "fun sum(a: Int, b: Int): Int"
        ),
        answer = "4"
    ),
    Question(
        question = "В чем ключевое отличие *Iterable<T>* и *Sequence<T>* в Kotlin?",
        options = listOf(
            "*Iterable<T>* работает только с immutable коллекциями, *Sequence<T>* применим к mutable",
            "Нет никакой разницы, т. к. *Sequence<T>* аналог *Iterable<T>*",
            "Последовательности обрабатываются лениво, итераторы жадно",
            "Последовательности обрабатываются по очереди, итераторы параллельно (многопоточно)"
        ),
        answer = "3"
    ),
    Question(
        question = "Чего не предлагает dataclass?",
        options = listOf(
            "Авто-генерируемый метод toString()",
            "Метод copy(...), для создания копии экземпляров.",
            "Автоматическое преобразование из/в JSON",
            "Авто-генерируемые методы hashCode() и equals()"
        ),
        answer = "3"
    ),
    Question(
        question = """
            Что выведет следующий код?
            *val listA = mutableListOf(1, 2, 3)*
            *val ListB = listA.add(4)*
            *print(listB)*
        """.trimIndent(),
        options = listOf(
            "[1, 2, 3, 4]",
            "True",
            "Ничего, тут ошибка компиляции",
            "Unit"
        ),
        answer = "2"
    ),
    Question(
        question = """
            Что применимо для следующего объявления класса?
            *class Person (val name: String)*
        """.trimIndent(),
        options = listOf(
            "Он package-private",
            "Он может быть расширен другими классами",
            "Он public",
            "У него приватное свойство name"
        ),
        answer = "3"
    ),
    Question(
        question = """
            В чем разница между a и b?
            *var a: String? = "text"*
            *var b: String = "text"*
        """.trimIndent(),
        options = listOf(
            "a является volatile, как в Java",
            "b является final и не может быть изменено",
            "a является final и не может быть изменено",
            "b никогда не сможет стать null"
        ),
        answer = "4"
    ),
    Question(
        question = """
            Какой тип у *arr*?
            *val arr = arrayOf(1, 2, 3)*
        """.trimIndent(),
        options = listOf(
            "Array<Int>",
            "Int[]",
            "int[]",
            "IntArray"
        ),
        answer = "1"
    ),
    Question(
        question = "Как в Kotlin правильно объявить переменную целочисленного типа?",
        options = listOf(
            "var i : int = 42",
            "let i = 42",
            "int i = 42",
            "var i : Int = 42"
        ),
        answer = "4"
    ),
    Question(
        question = "Для чего нужен оператор *!!*?",
        options = listOf(
            "Он возвращает левый операнд, если он не равен null, иначе возвращает правый операнд",
            "Это оператор модуля, аналог % в Java",
            "Он сравнивает два значения на тождественность",
            "Он преобразует любое значение в ненулевой тип и выбрасывает исключение, если значение равно null"
        ),
        answer = "4"
    ),
    Question(
        question = """Укажите правильный синтаксис для преобразования строки "42" в long""",
        options = listOf(
            """val l: Long = <Long>"42"
            """.trimMargin(),
            """val l: Long = "42".toLong()""",
            """val l: Long = (Long)"42"
            """.trimMargin(),
            """val l: Long = Long.parseLong("42")"""
        ),
        answer = "2"
    ),
    Question(
        question = "Что такое корутины *(coroutines)*?",
        options = listOf(
            "Функции, которые принимают другие функции в качестве аргументов или возвращают их.",
            "Штуки, обеспечивающие асинхронный код без блокировки потока.",
            "Термин Kotlin, используемый в методах класса.",
            "Автоматически сгенерированные методы hashCode() и equals() в data classes."
        ),
        answer = "2"
    ),
    Question(
        question = """
            Что делает этот код?
            *foo()()*
        """.trimIndent(),
        options = listOf(
            "Создает двумерный массив",
            "Не скомпилируется",
            "Вызывает асинхронно foo",
            "Вызывает функцию, которая вернется после вызова foo"
        ),
        answer = "4"
    ),
    Question(
        question = "Совместим ли Kotlin с Java?",
        options = listOf(
            "Kotlin может легко вызвать код Java, в то время как Java не может получить доступ к коду на Kotlin",
            "Kotlin предоставляет уровень совместимости для взаимодействия с Java, который становится доступен в рантайме",
            "Пока Kotlin запущен в JVM, он не может взаимодействовать с Java",
            "Kotlin может легко вызвать Java код и наоборот"
        ),
        answer = "4"
    ),
    Question(
        question = "В чем разница между val и var в Kotlin?",
        options = listOf(
            "Переменные, объявленные с помощью val, являются final, а переменные var – нет.",
            "Переменные, объявленные с помощью val, имеют доступ только к const членам.",
            "Переменные, объявленные с помощью var, являются final, а переменные val – нет.",
            "var ограничен видимостью ближайшего функционального блока, а у val видимость заканчивается на ({ })."
        ),
        answer = "1"
    ),
    Question(
        question = """
            Что выведет следующий код?
            *val list : List<Int> = listOf(1, 2, 3)*
                *list.add(4)*
                *print(list)*""".trimIndent(),
        options = listOf(
            "Он не компилируется, так как List не имеет метода add",
            "[1, 2, 3, 4]",
            "[5, 6, 7]",
            "Он не компилируется, из-за listOf"
        ),
        answer = "1"
    ),
    Question(
        question = """
            Что выведет этот код?
            *val a: String? = null*
            *val b: String = "Hello World"*
            *println(a==b)*""".trimIndent(),
        options = listOf(
            "Ничего, т. к. вылетит исключение NullPointerException",
            "Не скомпилируется",
            "false",
            "True"
        ),
        answer = "3"
    )
)