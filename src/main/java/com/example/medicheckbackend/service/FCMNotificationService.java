package com.example.medicheckbackend.service;

import com.example.medicheckbackend.domain.member.Member;
import com.example.medicheckbackend.domain.takemedicine.TakeMedicine;
import com.example.medicheckbackend.repository.MemberRepository;
import com.example.medicheckbackend.repository.TakeMedicineRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FCMNotificationService {
    private final FirebaseMessaging firebaseMessaging;

    private final TakeMedicineRepository takeMedicineRepository;

    @Scheduled(cron = "0 * * * * ?")
    public String sendNotificationByToken() {

        List<TakeMedicine> allByMember = takeMedicineRepository.findAll();

        DayOfWeek dayOfWeek = LocalDateTime.now().getDayOfWeek();
        int hour = LocalDateTime.now().getHour();
        int minute = LocalDateTime.now().getMinute();

        String temp = "";
        StringBuilder body = new StringBuilder();

        Map<Member, StringBuilder> takeMedicines = new HashMap<>();

        for (TakeMedicine takeMedicine : allByMember) {
            if (takeMedicine.getWeek().toString().equals(dayOfWeek.toString()) && takeMedicine.getMinute() == minute && takeMedicine.getHour() == hour) {
                temp = takeMedicine.getMedicine().getName() + " " + takeMedicine.getAmounts() + "개 ";
                body.append(temp);
                if (!takeMedicines.containsKey(takeMedicine.getMember())) {
                    takeMedicines.put(takeMedicine.getMember(), body);
                } else {
                    takeMedicines.get(takeMedicine.getMember()).append(body);
                }
            }
        }

        for (Member member : takeMedicines.keySet()) {
            if (member.getFirebaseToken() != null) {
                Notification notification = Notification.builder()
                        .setTitle("MediCheck")
                        .setBody(takeMedicines.get(member).append("복용해야할 시간입니다.").toString())
                        .build();

                Message message = Message.builder()
                        .setToken(member.getFirebaseToken())
                        .setNotification(notification)
                        .build();

                try {
                    firebaseMessaging.send(message);
                    return "알림을 성공적으로 전송했습니다. targetUserId=" + member.getId();
                } catch (FirebaseMessagingException e) {
                    e.printStackTrace();
                    return "알림 보내기를 실패하였습니다. targetUserId=" + member.getId();
                }
            }
            else {
                return "서버에 저장된 해당 유저의 FirebaseToken이 존재하지 않습니다. targetUserId=" + member.getId();
            }
        }

        return "전송 종료";
    }


    public String sendTemp() throws FirebaseMessagingException {
        Notification notification = Notification.builder()
                .setTitle("카톡봐라")
                .setBody("경수야 잘보고 있냐?")
                .build();

        Message message = Message.builder()
                .setToken("ercMZ6JLkke4hRD6Ibd_Ol:APA91bGLviJhFCVrIpzCEUik-WtROA-f-PKi7-hFvhfuhAWFMc8z_owjouNg0DctQLePlqhkHLWqRTZMuwEmrJmOEKrs5sSpfehE0BR2CIJwfarALVPHHuF75PtYmt_RCC0lxLFCkT_8")
                .setNotification(notification)
                .build();

        firebaseMessaging.send(message);
        return "전송 완료";
    }
}
