package xyz.bfdwdd.nshguildmanageb.base.invitationcode.service;

import xyz.bfdwdd.nshguildmanageb.base.invitationcode.dto.request.GenerateCodeRequest;
import xyz.bfdwdd.nshguildmanageb.base.invitationcode.dto.response.InvitationCodeResponse;

public interface InvitationCodeService {
    InvitationCodeResponse generateCode(GenerateCodeRequest request);
    boolean useCode(String codeStr);
}