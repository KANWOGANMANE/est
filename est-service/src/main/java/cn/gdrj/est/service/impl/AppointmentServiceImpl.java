package cn.gdrj.est.service.impl;

import cn.gdrj.est.constants.Constants;
import cn.gdrj.est.dao.AppointmentDao;
import cn.gdrj.est.entity.Appointment;
import cn.gdrj.est.service.AppointmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static cn.gdrj.est.constants.Constants.EST_NOT;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author jun
 * @date 2021/11/23
 */
@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentDao appointmentDao;

    @Override
    public int addAppointment(Appointment appointment) {
        appointment.setStatus(Constants.Order.AppointStatus.APPOINT);
        appointment.setCreatedtime(new Date());
        appointment.setIsdelete(EST_NOT);
        return appointmentDao.insert(appointment);
    }
}
