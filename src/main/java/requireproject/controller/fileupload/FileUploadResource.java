package requireproject.controller.fileupload;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import requireproject.common.file.FileUtil;
import requireproject.common.remind.JsonResult;
import requireproject.service.sysuser.SysUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ldy
 */
@RestController
@Api(value = "/fileupload",tags = {"需求-文件上传下载"})
@RequestMapping("/require/fileUpload/v1")
public class FileUploadResource {
    private static Logger logger = LoggerFactory.getLogger(FileUploadResource.class);

    @Autowired
    private SysUserService sysUserService;

    /*
     * 采用file.TranSto 来保存上传的文件
     */
    @PostMapping("/fileUpload1")
    @ApiOperation(value = "文件上传1",notes = "文件上传1",httpMethod = "POST",response = File.class)
    public  String  fileUpload2( @RequestParam ( "fileupload" ) MultipartFile file)  throws IOException {
        long   startTime=System.currentTimeMillis();
        System.out.println( "fileName：" +file.getOriginalFilename());
        String path= "D:\\test/" + new Date().getTime()+file.getOriginalFilename();

        File newFile= new File(path);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(newFile);
        long   endTime=System.currentTimeMillis();
        System.out.println( "采用file.TranSto的运行时间：" +String.valueOf(endTime-startTime)+ "ms" );
        return  "/success" ;
    }

    @PostMapping("/fileUpload2")
    @ApiOperation(value = "文件上传2",notes = "文件上传2",httpMethod = "POST",response = String.class)
    public ResponseEntity uploadFile(@RequestParam("fileupload") MultipartFile file) {
        long startTime = System.currentTimeMillis();
        String path = "";
        String originalFilename = file.getOriginalFilename();
        logger.info("fileName：" + originalFilename);
        int lastIndexOf = originalFilename.lastIndexOf(".");
        String fileType = originalFilename.substring(lastIndexOf + 1);
        //文件类型判断 doc,docx,jpg,png,xls
        logger.info("截取文件名类型:{}", fileType);
        if (fileType.equals("jpg") || fileType.equals("png") || fileType.equals("dox") || fileType.equals("docx") || fileType.equals("xls")) {
            path = "D:/filesss/" + new Date().getTime() + originalFilename;
            File newFile = new File(path);
            //通过CommonsMultipartFile的方法直接写文件(注意这个时候）
            try {
                file.transferTo(newFile);
                long endTime = System.currentTimeMillis();
                logger.info("采用file.TranSto的运行时间：" + String.valueOf(endTime - startTime) + "ms");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            return null;
        }
        return null;
    }


    @PostMapping("/fileUpload3")
    @ApiOperation(value = "文件上传3",notes = "文件上传3",httpMethod = "POST",response = String.class)
    public String add( @RequestParam( value="fileupload",required=false) MultipartFile multipartFile, HttpServletRequest request) {
        //视频上传
        //获取原文件名
        String name=multipartFile.getOriginalFilename();
        //获取文件后缀
        String subffix=name.substring(name.lastIndexOf(".")+1,name.length());
        //控制格式
        if(subffix.equals("")||!subffix.equals("mp4")||!subffix.equals("mov")||!subffix.equals("avi")||!subffix.equals("wmv")||!subffix.equals("m4v")||!subffix.equals("dat")||!subffix.equals("flv")||!subffix.equals("mkv"))
        {
            return null;
        }
        //新的文件名以日期命名
        String fileName=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        //获取项目路径到webapp
        String filepath=request.getServletContext().getRealPath("/")+"files\\";
        //获取项目根路径并转到static/videos
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/videos/";
        File file=new File(path);
        if(!file.exists())//文件夹不存在就创建
        {
            file.mkdirs();
        }
        //保存文件
        try {
            multipartFile.transferTo(new File(file+"\\"+fileName+"."+subffix));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String realPath=file+"\\"+fileName+"."+subffix;
        String simulationVideo="/videos/"+fileName+"."+subffix;
        return "132";
    }



    @PostMapping("/downLoad")
    @ApiOperation(value = "文件下载1",notes = "文件下载1",httpMethod = "POST",response = String.class)
    public void downLoad(@RequestParam("filePath") String filePath, HttpServletResponse response, @RequestParam("isOnLine") boolean isOnLine) throws Exception {
        File f = new File(filePath);
//        if (!f.exists()) {
//            response.sendError(404, "File not found!");
//            return;
//        }
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
        byte[] buf = new byte[1024];
        int len = 0;

        response.reset(); // 非常重要
        if (isOnLine) { // 在线打开方式
            URL u = new URL("fileupload:///" + filePath);
            response.setContentType(u.openConnection().getContentType());
            response.setHeader("Content-Disposition", "inline; filename=" + f.getName());
            // 文件名应该编码成UTF-8
        } else { // 纯下载方式
            response.setContentType("application/x-msDownLoad");
            response.setHeader("Content-Disposition", "attachment; filename=" + f.getName());
        }
        OutputStream out = response.getOutputStream();
        while ((len = br.read(buf)) > 0)
            out.write(buf, 0, len);
        br.close();
        out.close();
    }


    @PostMapping("/downloadLocal")
    @ApiOperation(value = "本地下载",notes = "本地下载",httpMethod = "POST",response = HttpServletResponse.class)
    public HttpServletResponse  downloadLocal(@RequestParam("filePath") String path,HttpServletResponse response) throws FileNotFoundException {
        try {
                   // path是指欲下载的文件的路径。
                   File file = new File(path);
                   // 取得文件名。
                   String filename = file.getName();
                   // 取得文件的后缀名。
                   String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

                   // 以流的形式下载文件。
                   InputStream fis = new BufferedInputStream(new FileInputStream(path));
                   byte[] buffer = new byte[fis.available()];
                   fis.read(buffer);
                   fis.close();
                   // 清空response
                   response.reset();
                   // 设置response的Header
                   response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
                   response.addHeader("Content-Length", "" + file.length());
                   OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
                   response.setContentType("application/octet-stream");
                   toClient.write(buffer);
                   toClient.flush();
                   toClient.close();
                 } catch (IOException ex) {
                   ex.printStackTrace();
                 }
            return response;
           }




    @GetMapping("/copyFile")
    @ApiOperation(value = "复制文件",notes = "复制文件",httpMethod = "GET",response = String.class)
    public JsonResult copyFile(@ApiParam("文件地址") @RequestParam("fileUrl") String fileUrl,
                               @ApiParam("文件复制地址") @RequestParam("newFileUrl") String newFileUrl) throws Exception {

        FileChannel input = null;
        FileChannel output = null;

        try {
            input = new FileInputStream(new File(fileUrl)).getChannel();
            output = new FileOutputStream(new File(newFileUrl)).getChannel();
            output.transferFrom(input, 0, input.size());
            return JsonResult.success(true);
        } catch (Exception e) {
            return JsonResult.error("309","文件复制错误");
        }

    }

    @GetMapping("/delFile")
    @ApiOperation(value = "删除文件夹",notes = "删除文件夹",httpMethod = "GET",response = String.class)
    public JsonResult delFile(@ApiParam("文件地址") @RequestParam("fileUrl") String fileUrl) throws Exception {


        try {
            FileUtil.delete(fileUrl);
            return JsonResult.success(true);
        } catch (Exception e) {
            return JsonResult.error("400","文件删除错误");
        }

    }


    @GetMapping("/moveFile")
    @ApiOperation(value = "移动文件",notes = "移动文件",httpMethod = "GET",response = String.class)
    public JsonResult moveFile(@ApiParam("文件地址") @RequestParam("fileUrl") String fileUrl,
                               @ApiParam("文件移动地址") @RequestParam("newFileUrl") String newFileUrl) throws Exception {


        try {
            FileUtil.moveFile(fileUrl,newFileUrl);
            return JsonResult.success(true);
        } catch (Exception e) {
            return JsonResult.error("309","文件复制错误");
        }

    }


}
