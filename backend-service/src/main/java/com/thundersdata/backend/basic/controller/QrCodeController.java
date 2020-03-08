package com.thundersdata.backend.basic.controller;

import com.google.zxing.WriterException;
import com.thundersdata.backend.basic.service.AuthService;
import com.thundersdata.backend.common.utils.QrCodeCreateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api(tags = "生成二维码接口")
@RequestMapping("qr")
@Controller
public class QrCodeController {

    @Autowired
    private AuthService authService;


    /**
     * 请求生成二维码的controller,传入参数为字符串,返回二维码的图片流
     *
     * @param qrCode
     * @param httpServletResponse
     * @return
     * @throws IOException
     * @throws WriterException
     */
    @ApiOperation(value = "生成二维码", notes = "生成文件流二维码")
    @GetMapping("generate")
    @ResponseBody
    public String generate(String qrCode, HttpServletResponse httpServletResponse) throws IOException, WriterException {

        Assert.notNull(qrCode, "参数qrCode不能为空！");

        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        QrCodeCreateUtil.createQrCode(outputStream, qrCode, 900, "png");
        // img为图片的二进制流
        outputStream.flush();

        return null;
    }

}
