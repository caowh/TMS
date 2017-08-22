/**
 * Created by user on 2017/8/21.
 */
function logout() {
    $.ajax({
        type: "get",
        url: "/main/logout.do",
        dataType: "json",
        success: function(res){
            if(res.code===1){
                window.location.href="/login.jsp"
            }else {
                alert(res.message)
            }
        }
    })
}
