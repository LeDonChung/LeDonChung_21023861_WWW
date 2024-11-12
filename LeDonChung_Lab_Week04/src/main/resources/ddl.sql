create table candidates
(
    id          int primary key auto_increment,
    last_name   varchar(70)        not null,
    middle_name varchar(70)        not null,
    first_name  varchar(70)        not null,
    dob         date               not null,
    email       varchar(70) unique not null,
    address     varchar(255),
    phone       varchar(15)
);

create table skills
(
    id          int primary key auto_increment,
    skill_name  varchar(70) not null,
    description varchar(255),
    field       varchar(70) not null
);

create table candidates_skills
(
    candidate_id int not null,
    skill_id     int not null,
    level        int not null check (level >= 1 and level <= 5),
    foreign key (candidate_id) references candidates (id),
    foreign key (skill_id) references skills (id),
    primary key (candidate_id, skill_id)
);

create table jobs
(
    id          int primary key auto_increment,
    description varchar(255)
);

create table jobs_skills
(
    job_id         int not null,
    skill_id       int not null,
    specific_level int not null check (specific_level >= 1 and specific_level <= 5),
    foreign key (job_id) references jobs (id),
    foreign key (skill_id) references skills (id),
    primary key (job_id, skill_id)
);
