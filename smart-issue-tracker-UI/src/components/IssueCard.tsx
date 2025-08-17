// card to display issue summary
"use client";
import Link from "next/link";

interface IssueCardProps {
  readonly id: string;
  readonly title: string;
  readonly createdAt: string;
  readonly summary?: string;
}


export default function IssueCard({ id, title, createdAt, summary }: IssueCardProps) {
  return (
    <Link href={`/issues/${id}`}>
      <div className="p-4 border rounded-lg shadow hover:bg-gray-50 transition cursor-pointer">
        <h3 className="font-semibold">{title}</h3>
        <p className="text-sm text-gray-500">{createdAt}</p>
        {summary && <p className="mt-2 text-gray-700">{summary}</p>}
      </div>
    </Link>
  );
}