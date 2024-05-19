import java.util.*

fun main() {
    println("Hello World!")
    // inmutables no se pueden reasignar "="
    //val la hace inmutable
    val inmutable: String = "Gabriel";
    //inmutable="Christian" //ERROR
    println(inmutable);

    //Mutables, si se pueden reaisgnar
    //var la hace mutable
    var mutable: String = "Gabriel";
    mutable = "Christian";
    println(mutable);

    //Duck typing
    //no es necesario colocar el tipo de dato en cierta variable
    var ejemploVariable = "Gabriel Nazate"
    val edadEjemplo: Int =12
    ejemploVariable.trim()

    //Si una variable ya es de un tipo no se le puede asignar otro tipo, normalmente, seria necesario convertirla
    //ejemploVariable = edadEjemplo

    //Variables primitivas
    val nombre:String = "Gabriel"
    val suelo:Double =3.14
    val estadoCivil:Char ='A'
    val mayorEdad:Boolean =true
    //Clases en Java
    val fechaNacimiento: Date = Date()
    
}