package zyb.design.ssm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import zyb.design.ssm.entity.Appointment;
import zyb.design.ssm.entity.Doctor;


@Mapper
public interface DoctorMapper extends BaseMapper<Doctor> {
    @Select({
            "<script>",
            "select d.*,a.* from doctor d",
            "left join appointment a on d.doctorID = a.doctorID",
            "<where>",
            "<if test='doctorID != null'>and d.doctorID = #{doctorID}</if>",
            "<if test='doctorName != null'>and d.doctorName like concat('%', #{doctorName}, '%')</if>",
            "<if test='doctorSpecialty != null'>and d.doctorSpecialty like concat('%', #{doctorSpecialty}, '%')</if>",
            "</where>",
            "</script>"
    })
    @Results({
            @Result(property = "doctorID", column = "doctorID"),
            @Result(property = "doctorName", column = "doctorName"),
            @Result(property = "doctorAge", column = "doctorAge"),
            @Result(property = "doctorGender", column = "doctorGender"),
            @Result(property = "doctorSpecialty", column = "doctorSpecialty"),
            @Result(property = "doctorPhone", column = "doctorPhone"),
            @Result(property = "appointment", column = "appointmentID", javaType = Appointment.class, one = @One(select = "zyb.design.ssm.mapper.AppointmentMapper.selectById"))
    })
    Page<Doctor> selectDoctorByPage(@Param("doctorID") Integer doctorID,
                                                 @Param("doctorName") String doctorName,
                                                 @Param("doctorSpecialty") String doctorSpecialty);

}
