package com.kq.impl.forUser;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kq.dao.forUser.INoteDao;
import com.kq.dao.IUserDao;
import com.kq.pojo.forUser.Note;
import com.kq.pojo.User;
import com.kq.service.forUser.INoteService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class NoteServiceImpl implements INoteService {
    @Resource
    INoteDao iNoteDao;
    @Resource
    IUserDao iUserDao;
    @Override
    public List<Note> getNoteByUserId(String userId){
        Sort sort = Sort.by(Sort.Direction.DESC, "noteId");//降序
        return iNoteDao.findNotesByUserUserId(userId,sort);
    }
    @Override
    public Note getNoteByNoteId( int noteId){
        return iNoteDao.findNoteByNoteId(noteId);
    }
    @Override
    public int saveNote(String noteExplain, MultipartFile[] files,String userId){
        Note note = new Note();
        note.setNoteExplain(noteExplain);
        User user = iUserDao.findUserByUserId(userId);
        note.setUser(user);
        List<String> imageUrls = new ArrayList<>();
        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    try {
                        // 确保上传目录存在
                        Path uploadDir = Paths.get("D:\\kouqiang-uploadImg");
                        if (!Files.exists(uploadDir)) {
                            Files.createDirectories(uploadDir);
                        }

                        // 生成唯一的文件名
                        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                        Path filePath = uploadDir.resolve(fileName);

                        // 保存文件
                        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                        // 生成图片访问 URL
                        String imageUrl = "/uploads/" + fileName;
                        imageUrls.add(imageUrl);
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to store image file", e);
                    }
                }
            }
        }
        note.setImageUrls(imageUrls);
        iNoteDao.save(note);
        return 1;
    }
    @Override
    public int updateNote ( int noteId,String retainedImageUrlsJson,MultipartFile[] files, String noteExplain){
        Note note = iNoteDao.findNoteByNoteId(noteId);
        note.setNoteExplain(noteExplain);
        // 解析之前保留的图片 URL
        List<String> retainedImageUrls = new ArrayList<>();
        if (retainedImageUrlsJson != null && !retainedImageUrlsJson.isEmpty()) {
            retainedImageUrls = new Gson().fromJson(retainedImageUrlsJson, new TypeToken<List<String>>() {}.getType());
        }
        // 删除不再保留的旧图片
        List<String> oldImageUrls = note.getImageUrls();
        if (oldImageUrls != null && !oldImageUrls.isEmpty()) {
            for (String oldImageUrl : oldImageUrls) {
                if (!retainedImageUrls.contains(oldImageUrl)) { // 只删除不再保留的图片
                    try {
                        Path oldFilePath = Paths.get("D:\\kouqiang-uploadImg", oldImageUrl.replace("/uploads/", ""));
                        Files.deleteIfExists(oldFilePath); // 删除旧文件
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to delete old image file", e);
                    }
                }
            }
        }
        // 保存新文件并更新图片 URL
        List<String> newImageUrls = new ArrayList<>(retainedImageUrls); // 保留之前的图片 URL
        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    try {
                        // 确保上传目录存在
                        Path uploadDir = Paths.get("D:\\kouqiang-uploadImg");
                        if (!Files.exists(uploadDir)) {
                            Files.createDirectories(uploadDir);
                        }
                        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                        Path filePath = uploadDir.resolve(fileName);
                        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                        String imageUrl = "/uploads/" + fileName;
                        newImageUrls.add(imageUrl); // 添加新图片 URL
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to store new image file", e);
                    }
                }
            }
        }

        // 更新 Note 对象的图片 URL
        note.setImageUrls(newImageUrls);

        iNoteDao.save(note);
        return 1;
    }
    @Override
    public int removeNote( int noteId, String userId){
        Note note = iNoteDao.findNoteByNoteId(noteId);
        User user = iUserDao.findUserByUserId(userId);
        user.removeNote(note);
        iUserDao.save(user);
        return 1;
    }
}

