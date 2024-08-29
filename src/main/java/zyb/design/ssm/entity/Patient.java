package zyb.design.ssm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Patient {
    @TableId(value = "patientID",type = IdType.AUTO)
    private Integer patientID;
    @TableField("patientName")
    private String patientName;
    @TableField("patientAge")
    private String patientAge;
    @TableField("patientGender")
    private String patientGender;
    @TableField("patientPhone")
    private String patientPhone;
}
