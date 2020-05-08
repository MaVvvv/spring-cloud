package cool.mawei.web;

import io.grpc.Channel;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import smartdialer.v1.ApgCampaignGrpc;
import smartdialer.v1.SmartDialerApg;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-03-15 16:47
 */
@Controller
public class CampaignController {

    @GrpcClient("apg-grpc-server")
    private Channel channel;

    private ApgCampaignGrpc.ApgCampaignBlockingStub blockingStub;

    @PostMapping(value = "/add")
    public String addCampaign(){
        blockingStub = ApgCampaignGrpc.newBlockingStub(channel);
        SmartDialerApg.CampaignBasicInfo campaignBasicInfo = SmartDialerApg.CampaignBasicInfo.newBuilder().setCampname("马伟").build();
        try {
            SmartDialerApg.ReplyCampBriefInfo campBriefInfo = blockingStub.addCampaign(campaignBasicInfo);
            System.out.println(campBriefInfo.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }
}
