package presentation.doctorpage


interface DoctorPageView {

    fun token(): String

    fun getFullName(): String

    fun startChat(token: String, title: String, userId: Int, anonymous: Boolean, doctorId: Int, avatar: String)

    fun showCreationError(e: Exception)

    fun goToChat(chatId: Int, avatar: String, title: String?)

    fun showDoctorInfo()
}