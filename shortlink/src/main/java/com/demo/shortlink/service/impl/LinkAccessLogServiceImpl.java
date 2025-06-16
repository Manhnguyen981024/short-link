package com.demo.shortlink.service.impl;

import com.demo.shortlink.model.LinkAccessLog;
import com.demo.shortlink.repository.LinkAccessLogRepository;
import com.demo.shortlink.service.LinkAccessLogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LinkAccessLogServiceImpl implements LinkAccessLogService {
    private final LinkAccessLogRepository linkAccessLogRepository;

    @Override
    public void save(LinkAccessLog linkAccessLog) {
        this.linkAccessLogRepository.save(linkAccessLog);
    }
}
