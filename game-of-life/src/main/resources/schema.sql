create table action
(action_id                  number          not null,
 action_name                varchar2(30)    not null,
 is_life_choice             char(1)         not null);

create table cell
(cell_id                    number          not null,
 cell_action_id             number          not null,
 next_cell_id               number          null,
 alt_next_cell_id           number          null);

create table career_type
(career_type_id             number          not null,
 career_type                varchar2(20)    not null,
 career_type_description    varchar2(30)    not null);

create table career
(career_id                  number          not null,
 career                     varchar2(20)    not null,
 career_description         varchar2(30)    not null,
 career_type_id             number          not null,
 career_salary              number          not null,
 career_max_salary          number          not null,
 career_taxes_due           number          not null);

create table player
(player_id                  varchar2(100)   not null,
 player_name                varchar2(20)    not null,
 career_id                  number          null,
 cash                       number          null,
 bank_loan                  number          null,
 number_spun                number          null,
 cell_position              number          null);

create table game
(game_id                    varchar2(100)   not null,
 num_players                number          not null);