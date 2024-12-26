package com.develop.revelryspringboot.constant.message;

public class ProductErrorMessage {
    public static final String PRODUCT_ID_NOT_BLANK = "ID sản phẩm không được để trống.";
    public static final String PRODUCT_NAME_NOT_BLANK = "Tên sản phẩm không được để trống.";
    public static final String PRODUCT_NAME_SIZE = "Tên sản phẩm phải có độ dài từ 1 đến 255 ký tự.";
    public static final String PRODUCT_DESCRIPTION_SIZE = "Mô tả sản phẩm phải có độ dài tối đa 255 ký tự.";
    public static final String PRODUCT_IMAGE_NOT_BLANK = "Hình ảnh sản phẩm không được để trống.";
    public static final String PRODUCT_PRICE_NOT_NULL = "Giá sản phẩm không được để trống.";
    public static final String PRODUCT_PRICE_INVALID = "Giá sản phẩm phải lớn hơn 0.";
    public static final String PRODUCT_PRICE_MINIMUM = "Giá sản phẩm phải lớn hơn hoặc bằng 1.";
    public static final String PRODUCT_CATEGORY_NOT_FOUND = "Danh mục sản phẩm không tồn tại.";
    public static final String PRODUCT_NOT_FOUND = "Không tìm thấy sản phẩm với ID đã cho.";
    public static final String PRODUCT_ALREADY_EXISTS = "Sản phẩm đã tồn tại.";
    public static final String PRODUCT_IS_ACTIVE_NOT_NULL = "Trạng thái hoạt động của sản phẩm không được để trống.";
}
