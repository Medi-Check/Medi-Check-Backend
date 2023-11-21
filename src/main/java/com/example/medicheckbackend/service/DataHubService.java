package com.example.medicheckbackend.service;

import static com.example.medicheckbackend.global.DataHub.DataHub.edgeAgent;

import java.util.Date;
import org.springframework.stereotype.Service;
import wisepaas.datahub.java.sdk.model.edge.EdgeData;
import wisepaas.datahub.java.sdk.model.edge.EdgeData.Tag;

@Service
public class DataHubService {

    public void sendData() {

        EdgeData data = new EdgeData();

        EdgeData.Tag FirstMedicine = new Tag();
        {
            FirstMedicine.DeviceId = "MediCheck";
            FirstMedicine.TagName = "FirstMedicine";
            FirstMedicine.Value = 90;
        }
        data.TagList.add(FirstMedicine);
        data.Timestamp = new Date();
        edgeAgent.SendData(data);

        data = new EdgeData();
        EdgeData.Tag SecondMedicine = new Tag();
        {
            SecondMedicine.DeviceId = "MediCheck";
            SecondMedicine.TagName = "SecondMedicine";
            SecondMedicine.Value = 75;
        }
        data.TagList.add(SecondMedicine);
        data.Timestamp = new Date();
        edgeAgent.SendData(data);

        data = new EdgeData();
        EdgeData.Tag ThirdMedicine = new Tag();
        {
            ThirdMedicine.DeviceId = "MediCheck";
            ThirdMedicine.TagName = "ThirdMedicine";
            ThirdMedicine.Value = 60;
        }
        data.TagList.add(ThirdMedicine);
        data.Timestamp = new Date();
        edgeAgent.SendData(data);

        EdgeData.Tag FourthMedicine = new Tag();
        {
            FourthMedicine.DeviceId = "MediCheck";
            FourthMedicine.TagName = "FourthMedicine";
            FourthMedicine.Value = 43;
        }
        data.TagList.add(FourthMedicine);
        data.Timestamp = new Date();
        edgeAgent.SendData(data);

        EdgeData.Tag FifthMedicine = new Tag();
        {
            FifthMedicine.DeviceId = "MediCheck";
            FifthMedicine.TagName = "FifthMedicine";
            FifthMedicine.Value = 30;
        }
        data.TagList.add(FifthMedicine);
        data.Timestamp = new Date();
        edgeAgent.SendData(data);
    }

    /**
     * 유통기한이 지난 비용을 조회
     */
    public void sendExpirationCost (int eCost, int nCost) {
        EdgeData data = new EdgeData();

        EdgeData.Tag ExpirationCost = new Tag();
        {
            ExpirationCost.DeviceId = "MediCheck";
            ExpirationCost.TagName = "ExpirationCost";
            ExpirationCost.Value = eCost;
        }
        data.TagList.add(ExpirationCost);
        data.Timestamp = new Date();
        edgeAgent.SendData(data);

        EdgeData.Tag nowCost = new Tag();
        {
            ExpirationCost.DeviceId = "MediCheck";
            ExpirationCost.TagName = "nowCost";
            ExpirationCost.Value = nCost;
        }
        data.TagList.add(nowCost);
        data.Timestamp = new Date();
        edgeAgent.SendData(data);
    }
}
