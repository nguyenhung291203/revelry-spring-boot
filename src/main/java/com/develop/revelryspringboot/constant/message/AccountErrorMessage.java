package com.develop.revelryspringboot.constant.message;

public class AccountErrorMessage {
    public static final String ACCOUNT_EXISTED = "Tài khoản đã tồn tại trong hệ thống.";
    public static final String EMAIL_EXISTED = "Địa chỉ email đã tồn tại.";
    public static final String ACCOUNT_NOT_EXISTED = "Tài khoản không tồn tại trong hệ thống.";
    public static final String USERNAME_INVALID =
            "Tên đăng nhập không hợp lệ. Tên đăng nhập phải bắt đầu bằng chữ cái, chỉ chứa ký tự chữ, số hoặc dấu gạch dưới.";
    public static final String EMAIL_INVALID =
            "Định dạng email không hợp lệ. Vui lòng nhập email đúng định dạng, ví dụ: abc@gmail.com.";
    public static final String USERNAME_EMPTY = "Tên đăng nhập không được để trống.";
    public static final String PASSWORD_EMPTY = "Mật khẩu không được để trống.";
    public static final String INCORRECT_PASSWORD = "Tên đăng nhập hoặc mật khẩu không chính xác.";
    public static final String ACCOUNT_LOCKED = "Tài khoản đã bị khóa. Vui lòng liên hệ hỗ trợ để được giúp đỡ.";
    public static final String USERNAME_NOT_BLANK = "Tên đăng nhập không được để trống.";
    public static final String PASSWORD_NOT_BLANK = "Mật khẩu không được để trống.";
    public static final String CONFIRM_PASSWORD_NOT_BLANK = "Xác nhận mật khẩu không được để trống.";
    public static final String PASSWORDS_NOT_MATCH = "Mật khẩu và xác nhận mật khẩu không khớp.";
    public static final String PASSWORD_FORMAT_INVALID =
            "Mật khẩu phải có ít nhất một chữ cái in hoa, một chữ cái thường và một chữ số.";
    public static final String ROLE_CODE_NOT_EXIST = "Mã vai trò không tồn tại trong hệ thống.";
}
