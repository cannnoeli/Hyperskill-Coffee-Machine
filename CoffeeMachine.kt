package machine

//The class that will represent a coffee machine, taking in user input via one of its methods
class CoffeeMachine {
    //Initiate variables that represent the coffee machine resources and the drinks that it can make
    private var water = 400
    private var milk = 540
    private var coffeeBeans = 120
    private var cups = 9
    private var money = 550

    private val espresso = mutableListOf(250, 0, 16, 4)
    private val latte = mutableListOf(350, 75, 20, 7)
    private val cappuccino = mutableListOf(200, 100, 12, 6)

    //Set the coffee machine's status to 0, which means it is on and waiting for a command - keep this variable private, but create a publicly accessible copy
    private var _status = 0
    var status = _status

    //Create a private method that returns the status of the coffee machine's resources
    private fun coffeeMachineStatus() = println("The coffee machine has:\n" +
            "$water ml of water\n" +
            "$milk ml of milk\n" +
            "$coffeeBeans g of coffee beans\n" +
            "$cups disposable cups\n" +
            "$$money of money")

    //Create a private method that allows users to buy coffee, as long as there are enough resources
    private fun buyCoffee(drink: MutableList<Int>) {
        if (water < drink[0]) {
            println("Sorry, not enough water!")
        } else if (milk < drink[1]) {
            println("Sorry, not enough milk!")
        } else if (coffeeBeans < drink[2]) {
            println("Sorry, not enough coffee beans!")
        } else if (cups == 0) {
            println("Sorry, not enough disposable cups!")
        } else {
            println("I have enough resources, making you a coffee!")
            water -= drink[0]
            milk -= drink[1]
            coffeeBeans -= drink[2]
            money += drink[3]
            cups--
        }
    }

    //Create a private method that refilss the coffee machine based on the user's input
    private fun fill(units: String, ing: String): Int {
        if (ing == "disposable cups") {
            println("Write how many $ing you want to add:")
            return readln().toInt()
        }
        println("Write how many $units of $ing you want to add:")
        return readln().toInt()
    }

    //Create the method that will take in the user's input and act based on that - the status will be updated based on the action taken by the user
    fun action(verb: String) {
        when (verb) {
            "remaining" -> coffeeMachineStatus()
            "buy" -> {
                println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
                when (readln()) {
                    "1" -> {
                        buyCoffee(espresso)
                    }
                    "2" -> {
                        buyCoffee(latte)
                    }
                    "3" -> {
                        buyCoffee(cappuccino)
                    }
                    "back" -> {}
                }
            }
            "fill" -> {
                water += fill("ml", "water")
                milk += fill("ml", "milk")
                coffeeBeans += fill("grams", "coffee beans")
                cups += fill("", "disposable cups")
            }
            "take" -> {
                println("I gave you $$money")
                money = 0
            }
            "exit" -> {
                this._status = 1
                status = _status
                return
            }
        }
        this._status = 0
        status = _status
        return
    }
}

//The main functions that creates the CoffeeMachine object and continues to ask for input until the user exits
fun main() {
    val coffeeMachine = CoffeeMachine()

    while (coffeeMachine.status == 0) {
        println("Write action (buy, fill, take, remaining, exit):")
        coffeeMachine.action(readln())
    }
}
