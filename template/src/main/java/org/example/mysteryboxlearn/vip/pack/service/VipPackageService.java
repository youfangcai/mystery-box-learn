package org.example.mysteryboxlearn.vip.pack.entity.service;

import org.example.mysteryboxlearn.vip.pack.entity.VipPackage;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class VipPackageService {
    private final VipPackageRepository vipPackageRepository;

}