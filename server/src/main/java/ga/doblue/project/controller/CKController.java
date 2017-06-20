package ga.doblue.project.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by SungHere on 2017-06-19.
 */
@RestController
@RequestMapping("/imageUpload")
public class CKController {


    @PostMapping /* 사진 업로드*/
    public void imageUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile upload) {

        FileOutputStream out = null;
        PrintWriter printWriter = null;
        response.setContentType("text/html;charset=UTF-8");


        try {

            String fileName = upload.getOriginalFilename();
            byte[] bytes = upload.getBytes();


            String uploadPath = "/usr/local/upload/" + fileName;//저장경로


            out = new FileOutputStream(new File(uploadPath));
            out.write(bytes);
            String callback = request.getParameter("CKEditorFuncNum");

            printWriter = response.getWriter();
            String fileUrl = "/usr/local/upload/" + fileName; // url경로


            printWriter.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("
                    + callback
                    + ",'"
                    + fileUrl
                    + "','이미지를 업로드 하였습니다.'"
                    + ")</script>");
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
