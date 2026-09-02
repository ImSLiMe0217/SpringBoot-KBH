package net.likelion.bebc25.sns.mapper;

import net.likelion.bebc25.sns.dto.PostResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PostMapper {
    // 게시글 id로 게시글 한 건의 정보 조회
    PostResponseDto findById(@Param("id") Long id);
}
