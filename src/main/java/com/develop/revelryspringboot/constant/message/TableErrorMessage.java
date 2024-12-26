package com.develop.revelryspringboot.constant.message;

public class TableErrorMessage {
    public static final String TABLE_NOT_FOUND = "Không tìm thấy bảng với ID đã cho.";
    public static final String TABLE_ALREADY_EXISTS = "Bảng với tên này đã tồn tại.";
    public static final String TABLE_NAME_NOT_BLANK = "Tên bảng không được để trống.";
    public static final String TABLE_NAME_SIZE = "Tên bảng phải có độ dài từ 1 đến 255 ký tự.";
    public static final String TABLE_CAPACITY_NOT_NULL = "Sức chứa của bảng không được để trống.";
    public static final String TABLE_CAPACITY_INVALID = "Sức chứa của bảng phải trên 1";
    public static final String TABLE_STATUS_INVALID = "Trạng thái của bảng không hợp lệ.";
    public static final String TABLE_IS_DELETED = "Bảng này đã bị xóa.";
}
