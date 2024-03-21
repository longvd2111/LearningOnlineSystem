
        function checkCategory(select) {
            var otherCategoryInput = document.getElementById('otherCategory');
            if (select.value === 'other') {
                otherCategoryInput.style.display = 'block';
            } else {
                otherCategoryInput.style.display = 'none';
            }
            if (navigator.userAgent.indexOf('MSIE') !== -1 || !!document.documentMode === true) {
    // Xử lý khi trình duyệt là IE
    // Đặt các đoạn mã JavaScript hoặc jQuery cần thực thi trong IE ở đây
    // Ví dụ:
    console.log("Trình duyệt đang sử dụng là Internet Explorer.");
    // Thêm mã JavaScript hoặc jQuery phù hợp ở đây
} else {
    // Xử lý khi trình duyệt không phải là IE
    // Đặt các đoạn mã JavaScript hoặc jQuery cho các trình duyệt khác ở đây
    // Ví dụ:
    console.log("Trình duyệt không phải là Internet Explorer.");
    // Thêm mã JavaScript hoặc jQuery cho các trình duyệt khác ở đây
}
        }
        
        

  