package org.xi.quick.filemanager.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.xi.quick.filemanager.entity.FileEntity;
import org.xi.quick.filemanager.mapper.FileMapper;
import org.xi.quick.filemanager.model.JsonResult;
import org.xi.quick.filemanager.utils.FileUtil;

@Controller
@RequestMapping({ "", "/", "/filemanager" })
public class FileManagerController {

    @Autowired
    private FileMapper fileMapper;

    @Value("#{commonProperties.upload_folder}")
    private String uploadFolder;
    
    @RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
    public String index(@RequestParam(value="folderId", defaultValue="0") Integer parentId, Model model) {

        List<FileEntity> files = fileMapper.selectByParentId(parentId);
        model.addAttribute("files", files);
        model.addAttribute("filesCount", files.size());
        model.addAttribute("parentId", parentId);

        return "filemanager/index";
    }

    @ResponseBody
    @RequestMapping(value = "/addfolder", method = RequestMethod.GET)
    public Integer addFolder(@RequestParam(value="folderId", defaultValue="0") Integer parentId, String name) {

        FileEntity entity = new FileEntity(parentId, name, "", 1, "");
        fileMapper.insert(entity);
        return entity.getId();
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Integer add(@RequestParam(value="folderId", defaultValue="0") Integer parentId, MultipartFile file) {

        String fileFullName = file.getOriginalFilename();
        String suffix = fileFullName.contains(".") ? fileFullName.substring(fileFullName.lastIndexOf(".")) : "";

        FileUtil.saveMultipartFile(file, uploadFolder, fileFullName);

        FileEntity entity = new FileEntity(parentId, fileFullName, "", 0, suffix);
        fileMapper.insert(entity);
        return entity.getId();
    }

    @ResponseBody
    @RequestMapping(value = "/multiadd", method = RequestMethod.POST)
    public List<Integer> add(@RequestParam(value="folderId", defaultValue="0") Integer parentId, MultipartFile[] files) {

        List<Integer> ids = new ArrayList<Integer>();
        for(MultipartFile file : files) {

            String fileFullName = file.getOriginalFilename();
            String suffix = fileFullName.contains(".") ? fileFullName.substring(fileFullName.lastIndexOf(".")) : "";

            FileUtil.saveMultipartFile(file, uploadFolder, fileFullName);

            FileEntity entity = new FileEntity(parentId, fileFullName, "", 0, suffix);
            Integer id = fileMapper.insert(entity);
            ids.add(id);
        }
        return ids;
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public JsonResult<Integer> delete(Integer id) {

        fileMapper.delete(id);
        return new JsonResult<Integer>((short)0, "", 1, 0l);
    }

}
