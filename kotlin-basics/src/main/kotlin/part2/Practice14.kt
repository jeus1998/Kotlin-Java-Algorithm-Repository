package part2

fun main() {
    println("=== 기본 인터페이스 ===")
    val myCar = MyCar("소나타")
    myCar.start()
    myCar.stop()
    myCar.honk()

    val myBike = MyBike("자전거")
    myBike.start()
    myBike.stop()
    myBike.ringBell()

    println("\n=== 다중 인터페이스 구현 ===")
    val smartphone = MySmartPhone("갤럭시", "010-1234-5678")
    smartphone.turnOn()
    smartphone.turnOff()
    smartphone.call("010-9876-5432")
    smartphone.sendMessage("안녕하세요!")
    smartphone.takePicture()
    smartphone.recordVideo()  // 디폴트 구현 사용

    println("\n=== 프로퍼티가 있는 인터페이스 ===")
    val pupil = Pupil("배제우", 28)
    println("이름: ${pupil.name}")
    println("나이: ${pupil.age}")
    pupil.study()
    pupil.introduce()
    println("성인 여부: ${if (pupil.isAdult) "성인" else "미성년자"}")

    val instructor = Instructor("배제우", 28, "수학")
    instructor.teach()
    instructor.introduce()

    println("\n=== 디폴트 메소드 ===")
    val sparrow = Sparrow("참새")
    sparrow.move()  // 디폴트 구현 사용
    sparrow.fly()

    val goldfish = Goldfish("금붕어")
    goldfish.move()  // 오버라이드한 구현 사용
    goldfish.swim()
}

// 1. 기본 인터페이스
interface Vehicle {
    fun start()
    fun stop()
}

// 2. Vehicle 구현 - MyCar
class MyCar(val model: String) : Vehicle {
    override fun start() {
        println("$model 시동을 켭니다")
    }

    override fun stop() {
        println("$model 시동을 끕니다")
    }

    fun honk() {
        println("빵빵!")
    }
}

// 3. Vehicle 구현 - MyBike
class MyBike(val type: String) : Vehicle {
    override fun start() {
        println("$type 페달을 밟기 시작합니다")
    }

    override fun stop() {
        println("$type 브레이크를 잡습니다")
    }

    fun ringBell() {
        println("따르릉!")
    }
}

// 4. 다중 인터페이스
interface Device {
    fun turnOn()
    fun turnOff()
}

interface Phone {
    fun call(number: String)
    fun sendMessage(message: String)
}

interface Camera {
    fun takePicture()
    fun recordVideo() {
        // 디폴트 구현
        println("비디오 녹화를 시작합니다")
    }
}

// 5. 다중 인터페이스 구현
class MySmartPhone(
    val model: String,
    val phoneNumber: String
) : Device, Phone, Camera {

    override fun turnOn() {
        println("$model 전원을 켭니다")
    }

    override fun turnOff() {
        println("$model 전원을 끕니다")
    }

    override fun call(number: String) {
        println("$phoneNumber 에서 $number 로 전화 중...")
    }

    override fun sendMessage(message: String) {
        println("메시지 전송: $message")
    }

    override fun takePicture() {
        println("$model 로 사진 촬영!")
    }
    // recordVideo는 디폴트 구현 사용
}

// 6. 프로퍼티가 있는 인터페이스
interface IPerson {
    val name: String  // 추상 프로퍼티
    val age: Int      // 추상 프로퍼티
    val isAdult: Boolean
        get() = age >= 18  // 커스텀 getter

    fun introduce() {
        // 프로퍼티를 사용하는 디폴트 구현
        println("저는 $name 이고, $age 살입니다")
    }
}

interface ILearner {
    fun study()
}

// 7. IPerson과 ILearner 구현
class Pupil(
    override val name: String,
    override val age: Int
) : IPerson, ILearner {
    override fun study() {
        println("$name 학생이 공부합니다")
    }
}

class Instructor(
    override val name: String,
    override val age: Int,
    val subject: String
) : IPerson {
    fun teach() {
        println("$name 선생님이 $subject 을(를) 가르칩니다")
    }
}

// 8. 디폴트 메소드가 있는 인터페이스
interface IAnimal {
    fun move() {
        println("동물이 움직입니다")
    }
}

class Sparrow(val species: String) : IAnimal {
    // move()는 디폴트 구현 사용
    fun fly() {
        println("$species 이(가) 날아갑니다")
    }
}

class Goldfish(val species: String) : IAnimal {
    override fun move() {
        println("$species 이(가) 헤엄칩니다")
    }

    fun swim() {
        println("$species 이(가) 물속을 누빕니다")
    }
}
