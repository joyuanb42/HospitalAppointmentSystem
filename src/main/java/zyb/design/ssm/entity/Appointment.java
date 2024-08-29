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
public class Appointment {
    @TableId(value = "appointmentID" ,type = IdType.AUTO)
    private Integer appointmentID;
    @TableField("patientID")
    private Integer patientID;
    @TableField("patientName")
    private String patientName;
    @TableField("doctorID")
    private Integer doctorID;
    @TableField("doctorName")
    private String doctorName;
    @TableField("patientPhone")
    private String patientPhone;
    @TableField("appointmentTime")
    private String appointmentTime;
    @TableField(exist = false)
    private String newAppointmentTime;
}
