package presentation.doctorpage


interface DoctorPageView {

    fun token(): String

    fun getFullName(): String

    fun createChat()

    fun showCreationError(e: Exception)

    fun openChat(chatId: Int, avatar: String, title: String?)

    fun showDoctorInfo()

    fun showLoader()

    fun hideLoader()

    fun makePhoneCall()
}