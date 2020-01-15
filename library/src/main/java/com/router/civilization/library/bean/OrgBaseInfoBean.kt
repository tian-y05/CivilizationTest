package com.wmsj.baselibs.bean

import java.io.Serializable

/**
 * Created by tian
on 2019/8/16.
 */
data class OrgBaseInfoBean(
        val account: String,
        val allactivitynum: String,
        val area1: String,
        val area2: String,
        val area3: String,
        val area4: String,
        val area5: String,
        val area_id: String,
        val certificate: String,
        val company_nature: String,
        val company_nature_id: String,
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
        val gps: String,
        val gpsname: String,
        val hang_up_org: String,
        val id: String,
        val if_join: Int,
        val inserttime: String,
        val is_register: String,
        val ischeck: Int,
        val join_info: String,
        val legal_person_back: String,
        val legal_person_just: String,
        val level: Any,
        val logo: String,
        val manage_org: String,
        val membernum: String,
        val message: String,
        val org1: Any,
        val org2: Any,
        val org3: Any,
        val org4: Any,
        val org5: Any,
        val org_cname: String,
        val org_code: String,
        val org_ename: Any,
        val org_extend_type: String,
        val org_extend_type_id: String,
        val org_type: String,
        val org_type_id: String,
        val parame1: String,
        val parame2: Any,
        val parame3: Any,
        val password: String,
        val phone: String,
        val seodesc: Any,
        val seokeyword: Any,
        val seotitle: Any,
        val service_count: String,
        val service_field: String,
        val service_field_id: String,
        val state: Int,
        val temporary_person: String,
        val time: String,
        val token: String,
        val tokenendtime: String,
        val type: Int,
        val web_banner_url: Any,
        val website_domain: Any,
        val website_isopen: Any,
        val website_name: Any,
        val wx_openid: Any,
        val zgarea: String,
        val zgorgname: String
) : Serializable {
    override fun toString(): String {
        return "OrgDetailBean(account='$account', allactivitynum=$allactivitynum, area1='$area1', area2='$area2', area3='$area3', area4=$area4, area5=$area5, area_id='$area_id', certificate='$certificate', company_nature='$company_nature', completion=$completion, contact='$contact', contact_mobile='$contact_mobile', createtime='$createtime', credit_code=$credit_code, desc='$desc', duty_back='$duty_back', duty_code='$duty_code', duty_just='$duty_just', duty_name='$duty_name', duty_tel='$duty_tel', email='$email', fax='$fax', fixed_person='$fixed_person', gps='$gps', gpsname='$gpsname', hang_up_org='$hang_up_org', id='$id', if_join=$if_join, inserttime='$inserttime', is_register='$is_register', ischeck=$ischeck, join_info='$join_info', legal_person_back='$legal_person_back', legal_person_just='$legal_person_just', level=$level, logo='$logo', manage_org='$manage_org', membernum=$membernum, message=$message, org1=$org1, org2=$org2, org3=$org3, org4=$org4, org5=$org5, org_cname='$org_cname', org_code='$org_code', org_ename=$org_ename, org_extend_type='$org_extend_type', org_extend_type_id='$org_extend_type_id', org_type='$org_type', parame1='$parame1', parame2=$parame2, parame3=$parame3, password='$password', phone='$phone', seodesc=$seodesc, seokeyword=$seokeyword, seotitle=$seotitle, service_count=$service_count, service_field='$service_field', service_field_id='$service_field_id', state=$state, temporary_person='$temporary_person', time=$time, token='$token', tokenendtime='$tokenendtime', type=$type, web_banner_url=$web_banner_url, website_domain=$website_domain, website_isopen=$website_isopen, website_name=$website_name, wx_openid=$wx_openid, zgarea='$zgarea', zgorgname='$zgorgname')"
    }
}