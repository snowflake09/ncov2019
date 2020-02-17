<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>日常出入登记</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"
          name="viewport"/>
    <link rel="icon" href="${ctx }/image/icon/logo.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="${ctx }/image/icon/logo.ico"/>
    <!-- CSS -->
    <link rel="stylesheet" href="${ctx }/js/ncov/inuser.css"/>
    <!-- Javascript -->
    <script src="${ctx }/js/jquery/jquery.min.js"></script>
    <script src="${ctx }/js/common/MD5_UTF8.js"></script>
    <script src="${ctx }/js/common/layer/layer.js"></script>
    <script src="${ctx }/js/common/app/util.js"></script>
<%--    <link href="${ctx }/js/bootstrap/bootstrap.min.css" rel="stylesheet">--%>

    <script>
        //如果登录窗口不在主窗口，在跳转到主窗口
        if (window != top) {
            top.location.href = "login";
        }
        function getuser(){
            $.post('${ctx}/ncov/web/getuser?wechatid=${openid}' , function(result) {
                if (result.code == 200) {
                    $('.username').val(result.data.name);
                    $('.card_no').val(result.data.cardno);
                    $('.phone').val(result.data.phone);
                    $('.provice_name').val(result.data.provincename);
                    $('.region_adress').val(result.data.regionaddress.substring(3));
                    $('.carno').val(result.data.carno);
                    $('.dog').val(result.data.dog);
                    var startaddress=result.data.regionaddress.split("-")[0];
                    $(".building").find("option:contains("+startaddress+")").attr("selected",true);
                }
            }, 'JSON');
        }
        jQuery(document)
            .ready(
                function () {
                    //回车事件绑定
                    document.onkeydown = function (event) {
                        var e = event || window.event
                            || arguments.callee.caller.arguments[0];
                        if (e && e.keyCode == 13) {
                            $('#submit').click();
                        }
                    };

                    //登录操作
                    $('#submit')
                        .click(
                            function () {
                                var openid = $('.openid').val();
                                var username = $('.username').val();
                                var phone = $('.phone').val();
                                var card_no = $('.card_no').val();
                                var provice_name = $('.provice_name').val();
                                var carno = $('.carno').val();
                                var dog = $('.dog').val();
                                var regionid = $('.regionid').val();
                                var region_adress = $('.region_adress').val();
                                var healthy = $('.healthy').val();
                                var building = $('.building').val();
                                if (username == '') {
                                    $('.error').fadeOut(
                                        'fast',
                                        function () {
                                            $('.error').css(
                                                'top',
                                                '20px')
                                                .show();
                                        });
                                    $('.error').fadeIn(
                                        'fast',
                                        function () {
                                            $('.username')
                                                .focus();
                                        });
                                    return false;
                                }

                                if (card_no == '' || !isCardNo(card_no)) {
                                    $('.error').fadeOut(
                                        'fast',
                                        function () {
                                            $('.error').css(
                                                'top',
                                                '80px')
                                                .show();
                                        });
                                    $(this).find('.error').fadeIn(
                                        'fast',
                                        function () {
                                            $('.card_no')
                                                .focus();
                                        });
                                    return false;
                                }
                                if (phone == '' || !checkPhone(phone)) {
                                    $('.error').fadeOut(
                                        'fast',
                                        function () {
                                            $('.error').css(
                                                'top',
                                                '140px')
                                                .show();
                                        });
                                    $(this).find('.error').fadeIn(
                                        'fast',
                                        function () {
                                            $('.phone')
                                                .focus();
                                        });
                                    return false;
                                }
                                if (provice_name == '') {
                                    $('.error').fadeOut(
                                        'fast',
                                        function () {
                                            $('.error').css(
                                                'top',
                                                '203px')
                                                .show();
                                        });
                                    $(this).find('.error').fadeIn(
                                        'fast',
                                        function () {
                                            $('.phone')
                                                .focus();
                                        });
                                    return false;
                                }
                                if (building == '') {
                                    $('.error').fadeOut(
                                        'fast',
                                        function () {
                                            $('.error').css(
                                                'top',
                                                '265px')
                                                .show();
                                        });
                                    $(this).find('.error').fadeIn(
                                        'fast',
                                        function () {
                                            $('.phone')
                                                .focus();
                                        });
                                    return false;
                                }
                                if (region_adress == '') {
                                    $('.error').fadeOut(
                                        'fast',
                                        function () {
                                            $('.error').css(
                                                'top',
                                                '325px')
                                                .show();
                                        });
                                    $(this).find('.error').fadeIn(
                                        'fast',
                                        function () {
                                            $('.phone')
                                                .focus();
                                        });
                                    return false;
                                }
                                if (healthy == '') {
                                    $('.error').fadeOut(
                                        'fast',
                                        function () {
                                            $('.error').css(
                                                'top',
                                                '520px')
                                                .show();
                                        });
                                    $(this).find('.error').fadeIn(
                                        'fast',
                                        function () {
                                            $('.healthy')
                                                .focus();
                                        });
                                    return false;
                                }
                                var data = {
                                    WECHATID:openid,
                                    NAME: username,
                                    PHONE: phone,
                                    CARDNO: card_no,
                                    PROVINCENAME: provice_name,
                                    CARNO: carno,
                                    DOG: dog,
                                    USERTYPE:1,
                                    REGIONID: regionid,
                                    REGIONADDRESS: building+"-"+region_adress,
                                    HEALTHY: healthy
                                };
                                var load = layer.load();
                                $.post(
                                    "${ctx}/ncov/web/addinuser",
                                    data,
                                    function (result) {
                                        layer
                                            .close(load);
                                        if (result.success
                                            && result.status != 200) {
                                            var status ;
                                            if (data.HEALTHY == 0){
                                                status ="健康"
                                            }else {
                                                status="不健康"
                                            }
                                            layer.msg('感谢您的配合，您已经登记：'+data.NAME+'，身份证：'+data.CARDNO+'，联系电话：'+data.PHONE+'，身体：'+status, {
                                                    time: 60000,
                                                    area:['auto', 'auto'],
                                                    anim:0,
                                                    shade:[0.5, '#393D49'],
                                                    btn: ['继续']
                                                },
                                                    function () {
                                                    });
                                            $('.username').val('');
                                            $('.phone').val('');
                                            $('.card_no').val('');
                                            $('.error').fadeOut();
                                            return;
                                        } else {
                                            layer.msg(result.msg, {
                                                    time: 60000,
                                                    area:['auto', 'auto'],
                                                    anim:0,
                                                    shade:[0.5, '#393D49'],
                                                    btn: ['继续']
                                                },
                                                function () {
                                                });
                                            $('.username').val('');
                                            $('.phone').val('');
                                            $('.card_no').val('');
                                            $('.error').fadeOut();
/*                                            setTimeout(
                                                function () {
                                                    //登录返回
                                                    window.location.href = '${ctx}'
                                                        + result.back_url
                                                        || "/";
                                                },
                                                1000)*/
                                        }
                                    }, "json");

                            });
                    $(
                        '.page-container form .username, .page-container form .password')
                        .keyup(
                            function () {
                                $(this).parent().find('.error')
                                    .fadeOut('fast');
                            });
                    getuser();

                });
    </script>
    <style>
        canvas {
            position: fixed;
            top: 0px;
            left: 0px;
        }
    </style>
</head>

<body id="body">
<div class="page-container">
    <h1>日常出入登记</h1>
    <div>${now}</div>
    <form id="_form" action="" method="post">
        <input name="openid" class="openid" type="hidden" value="${openid}">
        <input name="regionid" class="regionid" type="hidden" value="${regionid}">
        <input type="text" name="username" class="username" placeholder="姓名" maxlength="10">
        <input type="text" name="card_no" class="card_no" placeholder="18位身份证号码" maxlength="18">
        <input type="text" name="phone" class="phone" placeholder="联系方式：11位电话号码" maxlength="11">
		<input type="text" name="provice_name" class="provice_name" placeholder="户口所在地" maxlength="10">
        <select name="building" class="building">
            <option value="" selected>请选择住址楼号</option>
            <c:forEach items="${Buildings}" var="Building">
                <option value="${Building.BUILDINGNAME}">${Building.BUILDINGNAME}</option>
            </c:forEach>
        </select>
        <input type="text" name="region_adress" class="region_adress" placeholder="住址单元及门牌号如：1-110 " maxlength="40">
        <input type="text" name="carno" class="carno" placeholder="车牌号：如京NXXXXX" maxlength="40">
        <input type="text" name="dog" class="dog" placeholder="犬种" maxlength="40">
        <select name="healthy" class="healthy">
            <option value="" selected>身体是否健康</option>
            <option value="0">是</option>
            <option value="1">否</option>
        </select>
          <div style="text-align: left; margin-left: 10px;"></div>
<%--        <button type="button" id="Continue" style="width: 150px">继续登记</button>--%>
        <button type="button" id="submit" style="width: 100%">完成</button>
        <div class="error">
            <span>+</span>
        </div>
    </form>
</div>

</body>

</html>