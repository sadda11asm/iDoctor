package presentation.doctorpage


interface DoctorPageView {

    fun token(): String

    fun startChat(token: String, title: String, userId: Int, anonymous: Boolean, doctorId: Int, avatar: String)

    fun showError(e: Exception)

    fun goToChat(chatId: Int, avatar: String, title: String?)

    fun getFullName(): String
}