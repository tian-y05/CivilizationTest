package com.router.civilization.library.bean

/**
 * Created by tian
on 2019/8/16.
 */
data class OrgDetailBean(
        val account: String,
        val active_month: Int,
        val active_recordtime: Any,
        val active_state: Int,
        val active_threemonth: Int,
        val active_year: Int,
        val area_id: String,
        val certificate: Any,
        val company_nature: String,
        val completion: Int,
        val contact: String,
        val contact_mobile: String,
        val createtime: String,
        val credit_code: String,
        val desc: String,
        val duty_back: String,
        val duty_code: String,
        val duty_just: String,
        val duty_name: String,
        val duty_tel: String,
        val email: String,
        val fax: String,
        val fixed_person: String,
        val hang_up_org: String,
        val id: String,
        val if_join: Int,
        val inserttime: String,
        val is_register: Any,
        val ischeck: Int,
        val join_info: String,
        val legal_person_back: Any,
        val legal_person_just: Any,
        val level: String,
        val logo: Any,
        val manage_org: String,
        val membernum: String,
        val message: Any,
        val org_cname: String,
        val org_code: Any,
        val org_ename: String,
        val org_extend_type: String,
        val org_type: String,
        val parame1: Any,
        val parame2: Any,
        val parame3: Any,
        val password: String,
        val phone: String,
        val seodesc: Any,
        val seokeyword: Any,
        val seotitle: Any,
        val service_field: String,
        val set_time: String,
        val state: Int,
        val temporary_person: String,
        val time: String,
        val time_month: Int,
        val time_threemonth: Int,
        val time_year: Int,
        val token: Any,
        val tokenendtime: Any,
        val type: Int,
        val web_banner_url: Any,
        val website_domain: Any,
        val website_isopen: Any,
        val website_name: Any,
        val zgorgname: String,
        val zgarea: String,
        val allactivitynum: String
) {
    override fun toString(): String {
        return "OrgDetailBean(account=$account, active_month=$active_month, active_recordtime=$active_recordtime, active_state=$active_state, active_threemonth=$active_threemonth, active_year=$active_year, area_id='$area_id', certificate=$certificate, company_nature='$company_nature', completion=$completion, contact='$contact', contact_mobile='$contact_mobile', createtime='$createtime', credit_code='$credit_code', desc='$desc', duty_back='$duty_back', duty_code='$duty_code', duty_just='$duty_just', duty_name='$duty_name', duty_tel='$duty_tel', email='$email', fax='$fax', fixed_person='$fixed_person', hang_up_org='$hang_up_org', id='$id', if_join=$if_join, inserttime='$inserttime', is_register=$is_register, ischeck=$ischeck, join_info='$join_info', legal_person_back=$legal_person_back, legal_person_just=$legal_person_just, level='$level', logo=$logo, manage_org='$manage_org', membernum=$membernum, message=$message, org_cname='$org_cname', org_code=$org_code, org_ename='$org_ename', org_extend_type='$org_extend_type', org_type='$org_type', parame1=$parame1, parame2=$parame2, parame3=$parame3, password='$password', phone='$phone', seodesc=$seodesc, seokeyword=$seokeyword, seotitle=$seotitle, service_field='$service_field', set_time='$set_time', state=$state, temporary_person='$temporary_person', time=$time, time_month=$time_month, time_threemonth=$time_threemonth, time_year=$time_year, token=$token, tokenendtime=$tokenendtime, type=$type, web_banner_url=$web_banner_url, website_domain=$website_domain, website_isopen=$website_isopen, website_name=$website_name, zgorgname='$zgorgname', zgarea='$zgarea')"
    }
}