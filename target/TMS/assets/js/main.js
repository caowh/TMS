/**
 * Created by user on 2017/8/21.
 */
function logout() {
    $.ajax({
        type: "get",
        url: "/main/logout.do",
        dataType: "json",
        success: function(res){
            console.log(res)
            window.location.href="/login.jsp"
        }
    })
}
