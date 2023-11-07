package com.example.medicheckbackend.global.DataHub;

import wisepaas.datahub.java.sdk.EdgeAgent;
import wisepaas.datahub.java.sdk.common.Const;
import wisepaas.datahub.java.sdk.common.Const.EdgeType;
import wisepaas.datahub.java.sdk.common.EdgeAgentListener;
import wisepaas.datahub.java.sdk.common.Enum.ConnectType;
import wisepaas.datahub.java.sdk.common.Enum.Protocol;
import wisepaas.datahub.java.sdk.model.edge.ConfigAck;
import wisepaas.datahub.java.sdk.model.edge.DCCSOptions;
import wisepaas.datahub.java.sdk.model.edge.EdgeAgentOptions;
import wisepaas.datahub.java.sdk.model.edge.MQTTOptions;
import wisepaas.datahub.java.sdk.model.edge.TimeSyncCommand;
import wisepaas.datahub.java.sdk.model.edge.WriteValueCommand;
import wisepaas.datahub.java.sdk.model.event.DisconnectedEventArgs;
import wisepaas.datahub.java.sdk.model.event.EdgeAgentConnectedEventArgs;
import wisepaas.datahub.java.sdk.model.event.MessageReceivedEventArgs;

public class DataHub {

    public static EdgeAgent edgeAgent;
    public void connectDataHub() {
        EdgeAgentOptions options = new EdgeAgentOptions();

        options.ConnectType = ConnectType.DCCS;                // Connection type (DCCS, MQTT). The default setting is DCCS.

        options.DCCS = new DCCSOptions("757664e769adc974f2fe21d8aee5e254", "https://api-dccs-ensaas.sa.wise-paas.com/");

        options.MQTT = new MQTTOptions("127.0.0.1", 1883, "admin", "pwd", Protocol.TCP);

        options.UseSecure = false;
        options.AutoReconnect = true;
        options.NodeId = "9e75057d-360a-4eca-bf6e-3e3ddbe5e291";    // Obtain from portal
        options.Type = EdgeType.Gateway;                // Configure the edge as a Gateway or Device. The default setting is Gateway.
        options.DeviceId = "MediCheck";                // If the Type is Device, the DeviceID must be input.
        options.Heartbeat = 60000;                    // The default is 60 seconds.
        options.DataRecover = true;                    // Whether to recover data when disconnected

        edgeAgent = new EdgeAgent(options, agentListener);

        edgeAgent.Connect();
    }


    EdgeAgentListener agentListener = new EdgeAgentListener() {
        @Override
        public void Connected(wisepaas.datahub.java.sdk.EdgeAgent edgeAgent,
                              EdgeAgentConnectedEventArgs edgeAgentConnectedEventArgs) {
            System.out.println("Connected");
        }

        @Override
        public void Disconnected(wisepaas.datahub.java.sdk.EdgeAgent edgeAgent,
                                 DisconnectedEventArgs disconnectedEventArgs) {
            System.out.println("Disconnected");
        }

        @Override
        public void MessageReceived(wisepaas.datahub.java.sdk.EdgeAgent edgeAgent,
                                    MessageReceivedEventArgs e) {
            System.out.println("MessageReceived");
            switch (e.Type) {
                case Const.MessageType.WriteValue:
                    WriteValueCommand wvcMsg = (WriteValueCommand) e.Message;
                    for (WriteValueCommand.Device device : wvcMsg.DeviceList) {
                        System.out.println("DeviceID:" + device.Id);
                        for (WriteValueCommand.Tag tag : device.TagList) {
                            System.out.printf("TagName: %s, Value: %s\n", tag.Name, tag.Value.toString());
                        }
                    }
                    break;
                case Const.MessageType.TimeSync:
                    TimeSyncCommand tscMsg = (TimeSyncCommand) e.Message;
                    System.out.println("UTC Time:" + tscMsg.UTCTime.toString());
                    break;
                case Const.MessageType.ConfigAck:
                    ConfigAck cfgAckMsg = (ConfigAck) e.Message;
                    String result = cfgAckMsg.Result.toString();
                    break;
            }
        }
    };
}