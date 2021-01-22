import com.student.drop.BuildConfig

interface VApi {
    companion object {
        const val B_URL = BuildConfig.LGY_URL

//        // 登录
//        const val LOGIN = "api/user/login"
//
//        //获取验证码
//        const val GET_CAPTCHA = "api/user/captcha/vcode";
//
//        // 检测验证码
//        const val CHECK_CAPTCHA = "api/user/captcha/check";
//
//        // 注册 或者检查号码注册状态
//        const val REGISTER = "api/user/register2";
//
//        // 注册 第四步
//        const val REGISTER_COMEPLETE = "api/user/completeregister";
//
//        // 检测当前用户贷款状态
//        const val CHECK_USER_LOAN_STATUS = "v1/order/user/{userId}"
//
//        // 获取当前用户可以使用的贷款产品
//        const val USER_LOANS_PRODUCTS = "v1/order/preapply"
//
//        // 通过订单id,获取订单详细数据
//        const val ORDER_DETAILD = "v1/order/{orderId}"
//
//        // 获取协议以及文档接口
//        const val GET_DOCUMENT_URL = "api/user/files/loan/url";
//
//        // 获取个人信息
//        const val GET_PERSONAL_INFO = "api/user/info/personal";
//
//        // 上传文件,照片等
//        const val UPLOAD_FILE = "v1/file/upload"
//
//        // 获取地址明细
//        const val ADDRESS = "api/area/list.json?lastUpdate=05-01-2020%2000:00:00"
//
//        // 联系人信息
//        const val CONTACT_INFO = "api/user/info/contact"
//
//        // 用户通讯录zip上传
//        const val UPLOAD_CONTACT_LIST = "api/user/phone/upload"
//
//        // 获取Employment 信息
//        const val EMPLOYMENT_INFO = "api/user/info/employ"
//
//        // 贷款用途列表
//        const val LOAN_PURPOSE = "v1/system/dictionary/loanPurpose"
//
//        // 提交订单
//        const val SUBMIT_ORDER = "v1/order/apply"
    }

//    // 登录
//    @POST(LOGIN)
//    suspend fun login(
//        @Body loginData: LoginData
//    ): BaseResponse<LoginResult>
//
//    // 检测是否登录以及获取验证码
//    @FormUrlEncoded
//    @POST(GET_CAPTCHA)
//    suspend fun checkRegisterAndCaptcha(
//        @Field("phone") phone: String,
//        @Field("type") type: String
//    ): BaseResponse<CaptchaResult>
//
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