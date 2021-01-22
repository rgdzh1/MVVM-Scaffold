package com.student.drop.api

import com.student.drop.BuildConfig



interface EZLoanApiService {
    companion object {
        const val B_URL = BuildConfig.LGY_URL

        // 检测手机号是否注册
        const val CHECK_PHONE_EXIST = "/api/v1/checkPhone"

        // 注册发送短信
        const val SEND_CODE_REGISTER = "/api/v1/sendRegSms"

        // 注册
        const val REGISTER = "/api/v1/reg"

        // 登录
        const val LOGIN = "/api/v1/login"

        // 修改密码发送短信
        const val SEND_CODE_CHANGE_PWD = "/api/v1/sendEditPassSms"

        // 修改密码
        const val CHANGE_PWD = "/api/v1/editPass"

        // 获取认证时候需要的一些教育程度,婚姻状况等描述信息
        const val AUTH_HELP_INFO = "/api/v1/getChoice"

        // 获取OSS TOKEN
        const val OSS_TOKEN = "/api/v1/getOssAccessKey"

        // 上传文件名到服务器
        const val UPLOAD_FILE_NAME_SERVER = "/api/v1/getCardInfo"

        // 上传KYC信息
        const val UPLOAD_KYC_INFO = "/api/v1/saveUserFace"

        // 上传所有数据信息
        const val UPLOAD_Auth_ALL_INFO = "/api/v1/saveUserInfo"

        // APPList上传
        const val APPLIST_UPLOAD = "/api/v1/saveAppList"

        // 联系人列表上传
        const val CONTACT_LIST_UPLOAD = "/api/v1/savePhoneList"

        // 活体认证密钥
        const val GET_LIVENESS_LICENSE = "/api/v1/getLivensLicense"

        // 活体检测图片上传到服务器中
        const val UPLOAD_IMGE_LIVENESS = "/api/v1/saveUserImg"

        // 取款中心选择
        const val GET_LOAN_CENTER = "/api/v1/getProductInfo"

        // 获取银行卡列表
        const val GET_BANK_LIST = "/api/v1/getBankList"

        // 选择银行卡
        const val SELECT_BANK = "/api/v1/saveBankInfo"

        // IFSC CODE查询
        const val IFSC_CODE_QUERY = "/api/v1/getBankOutlets"
    }



//    @POST(LOGIN)
//    suspend fun login(
//        @Body loginData: LoginData
//    ): BaseResponse<LoginResult>

//    // 检测是否登录以及获取验证码
//    @FormUrlEncoded
//    @POST(GET_CAPTCHA)
//    suspend fun checkRegisterAndCaptcha(
//        @Field("phone") phone: String,
//        @Field("type") type: String
//    ): BaseResponse<CaptchaResult>

//    @FormUrlEncoded
//    @POST(CHECK_CAPTCHA)
//    suspend fun checkCaptcha(
//        @Field("phone") request: String,
//        @Field("type") type: String,
//        @Field("captcha") captcha: String
//    ): BaseResponse<CheckCaptchaResult>
//
//    @POST(REGISTER)
//    suspend fun register(
//        @Body data: RegisterData
//    ): BaseResponse<RegisterResult>
//
//    @POST(REGISTER_COMEPLETE)
//    suspend fun registerComeplete(
//        @Body data: RegisterCompleteData
//    ): BaseResponse<RegisterResult>
//
//    // 首页检查用户贷款状态
//    @GET(CHECK_USER_LOAN_STATUS)
//    suspend fun checkUserLoanStatus(
//        @Path("userId") id: String,
//        @Query("pageNum") page: Int = 1,
//        @Query("pageSize") pageSize: Int = 1
//    ): BaseResponse<UserLoanStatusResult>
//
//    // 获取当前用户可以使用的产品
//    @POST(USER_LOANS_PRODUCTS)
//    suspend fun userLoansProducts(): BaseResponse<UseLoansProductsResult>
//
//    // 获取用户的历史贷款数据
//    @GET(CHECK_USER_LOAN_STATUS)
//    suspend fun getOrderListData(
//        @Path("userId") id: String,
//        @Query("pageNum") page: Int,
//        @Query("pageSize") pageSize: Int
//    ): BaseResponse<UserLoanStatusResult>
//
//    // 根据订单id获取订单详细数据
//    @GET(ORDER_DETAILD)
//    suspend fun getOrderDetail(
//        @Path("orderId") id: String
//    ): BaseResponse<OrderInfo>
//
//    // 获取协议以及文档接口
//    @GET(GET_DOCUMENT_URL)
//    suspend fun getDocumentUrl(
//        @Query("userId") userId: String,
//        @Query("orderId") orderId: String,
//        @Query("type") type: String
//    ): BaseResponse<DocumentUrl>
//
//    // 获取文档以及隐私协议内容
//    @GET
//    suspend fun getDocumentContent(@Url url: String): Response<ResponseBody>
//
//    // 获取个人信息
//    @GET(GET_PERSONAL_INFO)
//    suspend fun getPersonalInfo(): BaseResponse<PersonalInfoRequest>
//
//    //修改个人信息
//    @PUT(GET_PERSONAL_INFO)
//    suspend fun updatePersonalInfo(@Body data: PersonalInfoData): BaseResponse<UpdateResult>
//
//    // 上传文件
//    @Multipart
//    @POST(UPLOAD_FILE)
//    suspend fun uploadFile(
//        @Query("dir") dir: String,
//        @Part multipartBody: MultipartBody.Part
//    ): BaseResponse<String>
//
//    //修改联系人信息
//    @PUT(CONTACT_INFO)
//    suspend fun updateContactInfo(@Body mContactInfoData: ContactInfoData): BaseResponse<UpdateResult>
//
//    //获取联系人信息
//    @GET(CONTACT_INFO)
//    suspend fun getContactInfo(): BaseResponse<ContactInfoData>
//
//    // 用户通讯录zip上传
//    @Multipart
//    @POST(UPLOAD_CONTACT_LIST)
//    suspend fun uploadContactList(
//        @Query("dir") dir: String,
//        @Part multipartBody: MultipartBody.Part
//    ): BaseResponse<UpdateResult>
//
//    //获取雇佣信息
//    @GET(EMPLOYMENT_INFO)
//    suspend fun getEmploymentInfo(): BaseResponse<EmployInfo>
//
//    //修改雇佣信息
//    @PUT(EMPLOYMENT_INFO)
//    suspend fun updateEmploymentInfo(@Body employInfo: EmployInfo): BaseResponse<UpdateResult>
//
//    //获取用户贷款用途列表
//    @GET(LOAN_PURPOSE)
//    suspend fun getLoanPurpose(): BaseResponse<LoanPurposeList>
//
//    // 获取借款协议
//    @GET(GET_DOCUMENT_URL)
//    suspend fun getLoanDocumentUrl(
//        @QueryMap mMap: MutableMap<String, String>
//    ): BaseResponse<DocumentUrl>
//
//    //  提交订单
//    @POST(SUBMIT_ORDER)
//    suspend fun submitOrder(
//        @Body order: OrderApply
//    ): BaseResponse<OrderInfo>
}