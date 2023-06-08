package tn.esprit.journaideapp.models

class MESinterv()
{
    internal var id : String ?= null
    internal var nom_interviewer: String? = null
    internal var nom_interviewee: String? = null
    internal var question: String? = null
    internal var reponse: String? = null


    // Getter id
    fun getId(): String? {
        return id
    }
    fun setId(newId: String?) {
        id = newId
    }

    fun getnom_interviewer(): String? {
        return nom_interviewer
    }


    fun getnom_interview(): String? {
        return nom_interviewee
    }

    fun getquestion(): String? {
        return question
    }


    fun getreponse(): String? {
        return reponse
    }

}
