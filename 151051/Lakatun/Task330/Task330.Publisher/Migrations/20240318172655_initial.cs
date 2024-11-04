using System;
using Microsoft.EntityFrameworkCore.Migrations;
using Npgsql.EntityFrameworkCore.PostgreSQL.Metadata;

#nullable disable

namespace Task330.Migrations
{
    /// <inheritdoc />
    public partial class initial : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "tbl_Creator",
                columns: table => new
                {
                    Id = table.Column<long>(type: "bigint", nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn),
                    Firstname = table.Column<string>(type: "text", maxLength: 64, nullable: false),
                    Lastname = table.Column<string>(type: "text", maxLength: 64, nullable: false),
                    Login = table.Column<string>(type: "text", maxLength: 64, nullable: false),
                    Password = table.Column<string>(type: "text", maxLength: 128, nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_tbl_Creator", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "tbl_Sticker",
                columns: table => new
                {
                    Id = table.Column<long>(type: "bigint", nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn),
                    Name = table.Column<string>(type: "text", maxLength: 32, nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_tbl_Sticker", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "tbl_News",
                columns: table => new
                {
                    Id = table.Column<long>(type: "bigint", nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn),
                    Title = table.Column<string>(type: "text", maxLength: 64, nullable: false),
                    Content = table.Column<string>(type: "text", maxLength: 2048, nullable: false),
                    Created = table.Column<DateTime>(type: "timestamp with time zone", nullable: false),
                    Modified = table.Column<DateTime>(type: "timestamp with time zone", nullable: false),
                    CreatorId = table.Column<long>(type: "bigint", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_tbl_News", x => x.Id);
                    table.ForeignKey(
                        name: "FK_tbl_News_tbl_Creator_CreatorId",
                        column: x => x.CreatorId,
                        principalTable: "tbl_Creator",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "tbl_News_Stickers",
                columns: table => new
                {
                    NewsId = table.Column<long>(type: "bigint", nullable: false),
                    StickerId = table.Column<long>(type: "bigint", nullable: false),
                    Id = table.Column<long>(type: "bigint", nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_tbl_News_Stickers", x => new { x.NewsId, x.StickerId });
                    table.ForeignKey(
                        name: "FK_tbl_News_Stickers_tbl_News_NewsId",
                        column: x => x.NewsId,
                        principalTable: "tbl_News",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_tbl_News_Stickers_tbl_Sticker_StickerId",
                        column: x => x.StickerId,
                        principalTable: "tbl_Sticker",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "tbl_Note",
                columns: table => new
                {
                    Id = table.Column<long>(type: "bigint", nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn),
                    Content = table.Column<string>(type: "text", maxLength: 2048, nullable: false),
                    NewsId = table.Column<long>(type: "bigint", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_tbl_Note", x => x.Id);
                    table.ForeignKey(
                        name: "FK_tbl_Note_tbl_News_NewsId",
                        column: x => x.NewsId,
                        principalTable: "tbl_News",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_tbl_News_CreatorId",
                table: "tbl_News",
                column: "CreatorId");

            migrationBuilder.CreateIndex(
                name: "IX_tbl_News_Stickers_StickerId",
                table: "tbl_News_Stickers",
                column: "StickerId");

            migrationBuilder.CreateIndex(
                name: "IX_tbl_Note_NewsId",
                table: "tbl_Note",
                column: "NewsId");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "tbl_News_Stickers");

            migrationBuilder.DropTable(
                name: "tbl_Note");

            migrationBuilder.DropTable(
                name: "tbl_Sticker");

            migrationBuilder.DropTable(
                name: "tbl_News");

            migrationBuilder.DropTable(
                name: "tbl_Creator");
        }
    }
}
