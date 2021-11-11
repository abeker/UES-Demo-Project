package com.practice.demo.service.implementation;

import com.practice.demo.dto.WriterDto;
import com.practice.demo.dto.mapper.WriterMapper;
import com.practice.demo.repository.IWriterRepository;
import com.practice.demo.service.interfaces.IWriterService;
import org.springframework.stereotype.Service;

@Service
public class WriterService implements IWriterService {

    private final IWriterRepository _writerRepository;

    public WriterService(IWriterRepository writerRepository) {
        _writerRepository = writerRepository;
    }

    @Override
    public void index(WriterDto writerDto) {
        _writerRepository.save(WriterMapper.mapModel(writerDto));
    }
}
