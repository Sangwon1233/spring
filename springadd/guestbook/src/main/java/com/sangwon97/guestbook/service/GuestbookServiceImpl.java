package com.sangwon97.guestbook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sangwon97.guestbook.domain.dto.GuestbookDto;
import com.sangwon97.guestbook.domain.dto.GuestbookListDTO;
import com.sangwon97.guestbook.domain.dto.GuestbookModifyDTO;
import com.sangwon97.guestbook.domain.dto.GuestbookViewDTO;
import com.sangwon97.guestbook.domain.dto.GuestbookWriteDTO;
import com.sangwon97.guestbook.domain.entity.Guestbook;
import com.sangwon97.guestbook.domain.entity.GuestbookEntity;
import com.sangwon97.guestbook.repository.GuestbookRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class GuestbookServiceImpl implements GuestbookService {
    private GuestbookRepository repository; //레포지토리 가져오기
    

    public List<GuestbookListDTO> list(){
        return repository.findAll().stream().map(GuestbookListDTO :: new).toList();
    };
    @Override
    public GuestbookViewDTO get(Long gno){
        Optional<Guestbook> opt = repository.findById(gno);
        if(!opt.isPresent()){
            return null;
        }
        return new GuestbookViewDTO(opt.get());
        // return repository.findById(gno).get().;
        // return new GuestbookViewDTO(repository.findById(gno).) 
        // 가져오는 애가 옵셔널이면 처음부터 생성자를 optional로 하면 된다
    }

    // @Override
    // public Long writer(GuestbookWriteDTO dto){
    //     repository.save(dto.toEntity());
    // }
    @Override
    public void modify(GuestbookModifyDTO dto){
        repository.save(dto.toEntity());
    }
    @Override
    public void remove(Long gno){
        repository.deleteById(gno);
    }
    @Override
    public Long writer(GuestbookDto dto){
        Guestbook guestbook = toEntity(dto);
        log.info(guestbook);
        repository.save(guestbook);
        log.info(guestbook);
        return guestbook.getGno();
    }

    // @Transactional
  // public void modify(Long id){
	// 	Optional<GuestbookEntitiy> gbe = repository.findById(id);
	// 	if(!gbe.isPresent()){ // gbe 관련 객체가 있는지 없는지에 관한 조건식
	// 		return;
	// 	}
	// 	gbe.ifPresent(entity-> {
	// 		GuestbookEntitiy modEntitiy = GuestbookEntitiy
	// 		.builder()
	// 			.gno(id)
	// 			.title(entity.getTitle())
	// 			.content(entity.getContent())
	// 			.writer(entity.getWriter())
	// 		.build();
	// 		repository.save(modEntitiy);
	// 	});
  // }

}
