package zyb.design.ssm.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Doctor extends BaseRowModel {

    @TableId(value = "doctorID", type = IdType.AUTO)
    @ExcelProperty(value = "医生ID", index = 0)
    private Integer doctorID;

    @TableField("doctorName")
    @ExcelProperty(value = "医生姓名", index = 1)
    private String doctorName;

    @TableField("doctorAge")
    @ExcelProperty(value = "医生年龄", index = 2)
    private String doctorAge;

    @TableField("doctorGender")
    @ExcelProperty(value = "医生性别", index = 3)
    private String doctorGender;

    @TableField("doctorSpecialty")
    @ExcelProperty(value = "医生专长", index = 4)
    private String doctorSpecialty;

    @TableField("doctorPhone")
    @ExcelProperty(value = "医生电话", index = 5)
    private String doctorPhone;

    @TableField(exist = false)
    private Appointment appointment;

    @TableField(exist = false)
    private Map<Integer, CellStyle> cellStyleMap;
}
