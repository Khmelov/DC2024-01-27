using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace Task340.Migrations
{
    /// <inheritdoc />
    public partial class unique : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateIndex(
                name: "IX_tbl_Sticker_Name",
                table: "tbl_Sticker",
                column: "Name",
                unique: true);

            migrationBuilder.CreateIndex(
                name: "IX_tbl_News_Title",
                table: "tbl_News",
                column: "Title",
                unique: true);

            migrationBuilder.CreateIndex(
                name: "IX_tbl_Creator_Login",
                table: "tbl_Creator",
                column: "Login",
                unique: true);
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropIndex(
                name: "IX_tbl_Sticker_Name",
                table: "tbl_Sticker");

            migrationBuilder.DropIndex(
                name: "IX_tbl_News_Title",
                table: "tbl_News");

            migrationBuilder.DropIndex(
                name: "IX_tbl_Creator_Login",
                table: "tbl_Creator");
        }
    }
}
