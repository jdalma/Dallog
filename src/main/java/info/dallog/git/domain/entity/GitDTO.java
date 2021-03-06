package info.dallog.git.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GitDTO {
    private String access_token;
    private String token_type;
    private String scope;
    private String data;

    public GitDTO(){};

    @Override
    public String toString() {
        return "GitDTO{" +
                "access_token='" + access_token + '\'' +
                ", token_type='" + token_type + '\'' +
                ", scope='" + scope + '\'' +
                '}';
    }
}
