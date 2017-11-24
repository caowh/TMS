package tms.spring.utils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by user on 2017/11/22.
 */
public class FileUtil {

    public static void saveFile(InputStream inputStream, String fileName,String path) {

        OutputStream os = null;
        try {
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件
            File tempFile = new File(path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            os = new FileOutputStream(tempFile.getPath() + File.separator + fileName);
            // 开始读取
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 完毕，关闭所有链接
            try {
                os.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void zip(String zipFileName,String sourceFileName) throws Exception {
        //创建zip输出流
        ZipOutputStream out = new ZipOutputStream( new FileOutputStream(zipFileName));

        File sourceFile = new File(sourceFileName);

        //调用函数
        compress(out,sourceFile,sourceFile.getName());

        out.close();
    }

    public static void writeStringToFile(String filePath,String content) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(content.getBytes("utf-8"));
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] readFile(File f) throws IOException {
        InputStream in = new FileInputStream(f);
        byte[] b=new byte[(int)f.length()];     //创建合适文件大小的数组
        in.read(b);    //读取文件中的内容到b[]数组
        in.close();
        return b;
    }

    private static void compress(ZipOutputStream out,File sourceFile,String base) throws Exception {
        //如果路径为目录（文件夹）
        if(sourceFile.isDirectory())
        {

            //取出文件夹中的文件（或子文件夹）
            File[] flist = sourceFile.listFiles();

            if(flist.length==0)//如果文件夹为空，则只需在目的地zip文件中写入一个目录进入点
            {
                out.putNextEntry(  new ZipEntry(base+File.separator) );
            }
            else//如果文件夹不为空，则递归调用compress，文件夹中的每一个文件（或文件夹）进行压缩
            {
                for(int i=0;i<flist.length;i++)
                {
                    compress(out,flist[i],base+File.separator+flist[i].getName());
                }
            }
        }
        else//如果不是目录（文件夹），即为文件，则先写入目录进入点，之后将文件写入zip文件中
        {
            out.putNextEntry( new ZipEntry(base) );
            FileInputStream fos = new FileInputStream(sourceFile);
            BufferedInputStream bis = new BufferedInputStream(fos);

            int tag;
            while((tag=bis.read())!=-1)
            {
                out.write(tag);
            }
            bis.close();
            fos.close();
        }
    }


    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
}
