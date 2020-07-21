package com.example.controller;

import com.example.model.VolumeModel;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@RestController
@RequestMapping("/")
public class VolumeController {

    @PostMapping("write")
    public String writeData(@RequestBody VolumeModel volumeModel) {
        File file = new File(volumeModel.getFilePath());
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(volumeModel.getMessage());
            fileWriter.close();
            return "SUCCESS";
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @GetMapping("read")
    public String readData(@RequestBody VolumeModel volumeModel) {
        File file = new File(volumeModel.getFilePath());

        String line = null;
        StringBuffer buffer = new StringBuffer();

        try {
            InputStream is = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return buffer.toString();
    }
}
