package xyz.bfdwdd.nshguildmanageb.functional.guild.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "guilds")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Guild {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "owner_id", nullable = false)
    private String ownerId;

    @Column(name = "max_members")
    private Integer maxMembers = 50;

    @Column(name = "status", nullable = false)
    private String status = "ACTIVE"; // ACTIVE, DISSOLVED

    @OneToMany(mappedBy = "guild", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GuildMember> members = new HashSet<>();

    public void addMember(GuildMember member) {
        members.add(member);
        member.setGuild(this);
    }

    public void removeMember(GuildMember member) {
        members.remove(member);
        member.setGuild(null);
    }
}